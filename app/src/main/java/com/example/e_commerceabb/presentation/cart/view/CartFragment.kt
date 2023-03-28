package com.example.e_commerceabb.presentation.cart.view

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
import com.example.e_commerceabb.databinding.FragmentCartBinding
import com.example.e_commerceabb.models.AddProductToCartRequest
import com.example.e_commerceabb.models.CartProductsRequest
import com.example.e_commerceabb.models.CartProductsResponse
import com.example.e_commerceabb.models.Order
import com.example.e_commerceabb.presentation.cart.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {
    lateinit var binding: FragmentCartBinding
    private val adapter by lazy { CartAdapter() }

    private var cartProductList = mutableListOf<Order>()
    private val viewModel: CartViewModel by viewModels({ this })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCartProducts()
        observeCartProducts()
        setListeners()
    }

    private fun observeCartProducts() {
        viewModel.cartProducts.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data -> setAdapterData(data) }
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

    private fun setListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setAdapterData(data: CartProductsResponse) {
        val cartData = data.products
        cartData.forEach { product ->
            val order = Order(
                product.id,
                product.product.imageUrls[0],
                product.product.name,
                "US $${product.product.currentPrice}",
                count = product.cartQuantity
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
        adapter.onPlusClick = { productId, count ->
            val request = AddProductToCartRequest(
                listOf(
                    CartProductsRequest(
                        count,
                        productId
                    )
                )
            )
//            viewModel.updateCartProducts(request) was not good idea
        }
        adapter.onMinusClick = { productId ->
            viewModel.decreaseProductQuantity(productId)
        }
        adapter.onDeleteClick = { productId ->
            viewModel.deleteProduct(productId)
        }
    }
}
