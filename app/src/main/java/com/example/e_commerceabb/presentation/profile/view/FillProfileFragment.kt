package com.example.e_commerceabb.presentation.profile.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_commerceabb.BuildConfig
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentFillProfileBinding
import com.example.e_commerceabb.models.GetCustomerResponse
import com.example.e_commerceabb.models.UpdateCustomerRequest
import com.example.e_commerceabb.presentation.profile.viewmodel.FillProfileViewModel
import com.example.e_commerceabb.utils.Constants
import com.example.e_commerceabb.utils.Constants.EMPTY
import com.example.e_commerceabb.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class FillProfileFragment : Fragment(R.layout.fragment_fill_profile) {
    lateinit var binding: FragmentFillProfileBinding
    private val pickImage = 100
    private var avatar: String? = null
    private val viewModel: FillProfileViewModel by viewModels({ this })

    @Inject
    lateinit var tokenManager: TokenManager

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
        binding = FragmentFillProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleAvatar()
        handleInputs()
        observeUpdateCustomer()
        handleEditProfile()
        handleLogOut()
        handleChangePassword()
        setListeners()
        handleContinueButton()
        handleAdminButtons()
        initView()
        viewModel.getCustomer()
    }

    private fun handleLogOut() {
        binding.logOut.setOnClickListener {
            val sharedPreferences =
                context?.getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.putBoolean(Constants.IS_USER_REGISTERED, false)?.apply()
            tokenManager.removeToken()
            findNavController().navigate(R.id.action_fillProfileFragment_to_onboardingFragment)
        }
    }

    private fun handleChangePassword() {
        binding.changePassword.setOnClickListener {
            findNavController().navigate(R.id.passwordRecoveryFragment)
        }
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getCustomerData(data: GetCustomerResponse) {
        viewModel.setIsAdmin(data.isAdmin)

        binding.apply {
            progress.visibility = View.GONE
            profile.visibility = View.VISIBLE
            container.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.transparent
                )
            )
            data.avatarUrl?.let { profileAvatarImg.setImageURI(getFileUri(it)) }
            profileAvatarImg.scaleType = ImageView.ScaleType.CENTER_CROP
            profileAvatarImg.setBackgroundResource(R.drawable.rounded_image)
            fullName.setText(data.fullName)
            username.setText(data.login)
            email.setText(data.email)
            dateOfBirth.setText(data.dateOfBirth)
            phoneNumber.setText(data.telephone)
            gender.setText(data.gender)
            if (data.isAdmin) {
                addNewProduct.visibility = View.VISIBLE
                seeMyProducts.visibility = View.VISIBLE
            }
        }
    }

    private fun handleAdminButtons() {
        binding.seeMyProducts.setOnClickListener {
            findNavController().navigate(R.id.addedProductsFragment)
        }
        binding.addNewProduct.setOnClickListener {
            findNavController().navigate(R.id.action_fillProfileFragment_to_addProductFragment)
        }
    }

    private fun getFileUri(filePath: String): Uri {
        val file = File(filePath)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID + ".provider",
                file
            )
        } else {
            Uri.fromFile(file)
        }
        return uri
    }

    private fun initView() {
        binding.progress.visibility = View.VISIBLE
        binding.container.visibility = View.VISIBLE
    }

    private fun handleAvatar() {
        binding.profileAvatar.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    private fun openGallery() {
        val gallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            binding.profileAvatarImg.setImageURI(data?.data)
            avatar = getRealPathFromUri(data?.data)
            binding.profileAvatarImg.scaleType = ImageView.ScaleType.CENTER_CROP
            binding.profileAvatarImg.setBackgroundResource(R.drawable.rounded_image)
        }
    }

    private fun handleEditProfile() {
        binding.editProfile.setOnClickListener { setInputVisibilities(true) }
    }

    private fun setInputVisibilities(visible: Boolean) {
        binding.apply {
            if (visible.not()) {
                fullName.clearFocus()
                username.clearFocus()
                email.clearFocus()
                dateOfBirth.clearFocus()
                phoneNumber.clearFocus()
                gender.clearFocus()
            }
            fullName.isFocusableInTouchMode = visible
            username.isFocusableInTouchMode = visible
            email.isFocusableInTouchMode = visible
            dateOfBirth.isFocusableInTouchMode = visible
            phoneNumber.isFocusableInTouchMode = visible
            gender.isFocusableInTouchMode = visible
            btnSkip.isVisible = visible
            btnContinue.isVisible = visible
            addNewProduct.isVisible = !visible
            seeMyProducts.isVisible = !visible
        }
    }

    private fun handleInputs() {
        binding.dateOfBirth.addTextChangedListener {
            handleContinueButton()
            binding.dateOfBirth.compoundDrawableTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.primaryDark
            )
        }
        binding.username.addTextChangedListener {
            handleContinueButton()
        }
        binding.fullName.addTextChangedListener {
            handleContinueButton()
        }
        binding.gender.addTextChangedListener {
            handleContinueButton()
        }
        binding.email.addTextChangedListener {
            handleContinueButton()
        }
        binding.phoneNumber.addTextChangedListener {
            handleContinueButton()
        }
    }

    private fun observeUpdateCustomer() {
        viewModel.customer.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { it1 -> getCustomerData(it1) }
                    setInputVisibilities(false)
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

    private fun getRealPathFromUri(uri: Uri?): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri!!, projection, null, null, null)
        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        val filePath = columnIndex?.let { cursor?.getString(it) }
        cursor?.close()
        return filePath ?: ""
    }

    private fun handleContinueButton() {
        val usernameIsNotEmpty = binding.username.text?.isNotEmpty() == true
        val emailIsNotEmpty = binding.email.text?.isNotEmpty() == true
        val dateOfBirthIsNotEmpty = binding.dateOfBirth.text?.isNotEmpty() == true
        val genderIsNotEmpty = binding.gender.text?.isNotEmpty() == true
        val fullNameIsNotEmpty = binding.fullName.text?.isNotEmpty() == true
        val phoneNumberIsNotEmpty = binding.phoneNumber.text?.isNotEmpty() == true
        val isButtonOkay =
            usernameIsNotEmpty || emailIsNotEmpty || dateOfBirthIsNotEmpty || genderIsNotEmpty || fullNameIsNotEmpty || phoneNumberIsNotEmpty || avatar.isNullOrEmpty()
                .not()
        if (isButtonOkay) {
            binding.btnContinue.isClickable = true
            binding.btnContinue.alpha = 1f
        } else {
            binding.btnContinue.isClickable = false
            binding.btnContinue.alpha = 0.3f
        }
        binding.btnContinue.setOnClickListener {
            val email =
                if (binding.email.text.toString() == EMPTY) null else binding.email.text.toString()
            val fullName =
                if (binding.fullName.text.toString() == EMPTY) null else binding.fullName.text.toString()
            val username =
                if (binding.username.text.toString() == EMPTY) null else binding.username.text.toString()
            val dateOfBirth =
                if (binding.dateOfBirth.text.toString() == EMPTY) null else binding.dateOfBirth.text.toString()
            val phoneNumber =
                if (binding.phoneNumber.text.toString() == EMPTY) null else binding.phoneNumber.text.toString()
            val gender =
                if (binding.gender.text.toString() == EMPTY) null else binding.gender.text.toString()

            val customerRequest = UpdateCustomerRequest(
                dateOfBirth = dateOfBirth,
                fullName = fullName,
                telephone = phoneNumber,
                gender = gender,
                login = username,
                email = email,
                avatarUrl = avatar
            )
            viewModel.updateCustomer(customerRequest)
        }
        binding.btnSkip.setOnClickListener {
            setInputVisibilities(false)
        }
    }
}
