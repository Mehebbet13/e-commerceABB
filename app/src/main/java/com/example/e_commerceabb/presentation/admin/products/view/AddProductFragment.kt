package com.example.e_commerceabb.presentation.admin.products.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentAddProductBinding
import com.example.e_commerceabb.models.AddProductRequest
import com.example.e_commerceabb.presentation.admin.products.viewmodel.AddProductViewModel
import com.example.e_commerceabb.utils.Jwt
import com.example.e_commerceabb.utils.TokenManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddProductFragment : Fragment(R.layout.fragment_add_product) {
    lateinit var binding: FragmentAddProductBinding
    private var pickImage = 10
    private val imageList = arrayListOf<String>()
    private val viewModel: AddProductViewModel by viewModels({ this })

    // Create a storage reference from our app
    var storageRef = Firebase.storage

    @Inject
    lateinit var tokenManager: TokenManager

    private var token: String? = null
    private var userId: String? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Storage permission denied",
                    Toast.LENGTH_SHORT
                ).show()
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    Toast.makeText(
                        requireContext(),
                        "You previously denied storage permission," +
                            "You can manually change it from app settings.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // User has previously denied the permission, show rationale dialog
                } else {
                    // User has previously denied the permission and selected "Don't ask again"
                    // Show dialog explaining how to grant the permission manually
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleContinueButton()
        setInputs()
        handleTextChangedListeners()
        addImages()
        observeProducts()
        storageRef = FirebaseStorage.getInstance()
        binding.addNewProduct.setOnClickListener {
            addProduct()
        }

        token = tokenManager.getToken()
        userId = token?.let { Jwt.getUserId(it) }
    }

    private fun observeProducts() {
        viewModel.addedProduct.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.addedProductsFragment)
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun openGallery() {
        val gallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.data?.let { addImagesToFirebase(it, "$requestCode.jpg") }
            val imageRef = Firebase.storage.reference.child("/Images/$requestCode.jpg")
            imageRef.downloadUrl
                .addOnSuccessListener { uri ->
                    // Handle successful download URL generation
                    val imageUrl = uri.toString()
                    imageList.add(imageUrl)
                }
                .addOnFailureListener { exception ->
                    // Handle any errors
                    Log.e("TAG", "Error getting download URL", exception)
                }
            when (requestCode) {
                10 -> {
                    binding.addedImage.setImageURI(data?.data)
                    binding.addImage.visibility = View.GONE
                }
                20 -> {
                    binding.addedImageSecond.setImageURI(data?.data)
                    binding.addImageSecond.visibility = View.GONE
                }
                30 -> {
                    binding.addedImageThird.setImageURI(data?.data)
                    binding.addImageThird.visibility = View.GONE
                }
                40 -> {
                    binding.addedImageForth.setImageURI(data?.data)
                    binding.addImageForth.visibility = View.GONE
                }
            }
        }
    }

    private fun addImagesToFirebase(uri: Uri, imageName: String) {
        storageRef.getReference("Images").child(imageName)
            .putFile(uri)
    }

    private fun handleContinueButton() {
        val nameIsNotEmpty = binding.name.text?.isNotEmpty() == true
        val descriptionIsNotEmpty = binding.description.text?.isNotEmpty() == true
        val priceIsNotEmpty = binding.price.text?.isNotEmpty() == true
        val brandIsNotEmpty = binding.brand.text?.isNotEmpty() == true
        val categoryIsNotEmpty = binding.category.text?.isNotEmpty() == true
        val isButtonOkay =
            nameIsNotEmpty || descriptionIsNotEmpty || priceIsNotEmpty || brandIsNotEmpty || categoryIsNotEmpty || imageList.isNullOrEmpty()
                .not()
        if (isButtonOkay) {
            binding.addNewProduct.isClickable = true
            binding.addNewProduct.alpha = 1f
        } else {
            binding.addNewProduct.isClickable = false
            binding.addNewProduct.alpha = 0.3f
        }
    }

    private fun addProduct() {
        val name = binding.name.text.toString()
        val description = binding.description.text.toString()
        val price = binding.price.text.toString().toDouble()
        val brand = binding.brand.text.toString()
        val category = binding.category.text.toString()
        val request = AddProductRequest(
            name = name,
            description = description,
            currentPrice = price,
            brand = brand,
            categories = category,
            imageUrls = imageList,
            userId = userId
        )
        viewModel.addProduct(request)
    }

    private fun setInputs() {
        binding.apply {
            if (name.text?.isNotEmpty() == true) {
                name.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                name.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
            if (description.text?.isNotEmpty() == true) {
                description.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                description.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
            if (price.text?.isNotEmpty() == true) {
                price.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                price.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
            if (category.text?.isNotEmpty() == true) {
                category.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                category.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
            if (brand.text?.isNotEmpty() == true) {
                brand.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.black
                )
            } else {
                brand.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.grey20
                )
            }
        }
    }

    private fun handleTextChangedListeners() {
        binding.apply {
            name.addTextChangedListener {
                setInputs()
                handleContinueButton()
            }
            description.addTextChangedListener {
                setInputs()
                handleContinueButton()
            }
            price.addTextChangedListener {
                setInputs()
                handleContinueButton()
            }
            brand.addTextChangedListener {
                setInputs()
                handleContinueButton()
            }
            category.addTextChangedListener {
                setInputs()
                handleContinueButton()
            }
        }
    }

    private fun addImages() {
        binding.addFirstImage.setOnClickListener {
            pickImage = 10
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        binding.addSecondImage.setOnClickListener {
            pickImage = 20
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        binding.addThirdImage.setOnClickListener {
            pickImage = 30
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        binding.addForthImage.setOnClickListener {
            pickImage = 40
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }
}
