package com.example.e_commerceabb.presentation.admin.products.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentAddedProductsBinding
import com.example.e_commerceabb.models.FilteredResponse
import com.example.e_commerceabb.models.Order
import com.example.e_commerceabb.presentation.admin.products.viewmodel.AddProductViewModel
import com.example.e_commerceabb.utils.Constants.EMPTY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddedProductsFragment : Fragment(R.layout.fragment_added_products) {
    lateinit var binding: FragmentAddedProductsBinding
    private val adapter by lazy { AddedProductsAdapter() }
    private val viewModel: AddProductViewModel by viewModels({ this })
    private var cartProductList = mutableListOf<Order>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddedProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAddedProducts()
        observeProducts()
        setListeners()
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeProducts() {
        viewModel.addedProducts.observe(viewLifecycleOwner) {
            binding.progress.visibility = View.GONE
            when (it) {
                is Resource.Success -> {
                    if (it.data?.products?.isEmpty() == true) {
                        binding.emptyTitle.visibility = View.VISIBLE
                    } else {
                        binding.emptyTitle.visibility = View.GONE
                    }
                    it.data?.let { data -> setAdapterData(data) }
                }
                is Resource.Error -> {
                    binding.emptyTitle.visibility = View.VISIBLE
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setAdapterData(data: FilteredResponse) {
        val productData = data.products
        productData.forEach { product ->
            val order = Order(
                product.id ?: EMPTY,
                product.imageUrls?.getOrNull(0) ?: EMPTY,
                product.name ?: EMPTY,
                "US $${product.currentPrice}"
            )
            cartProductList.add(order)
        }
        setAdapter()
        adapter.setData(cartProductList)
    }

    private fun setAdapter() {
        binding.cartProductsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.cartProductsRv.adapter = adapter
    }
}
