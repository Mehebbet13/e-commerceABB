package com.example.e_commerceabb.presentation.cart.view

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentCartBinding
import com.example.e_commerceabb.models.*
import com.example.e_commerceabb.presentation.cart.viewmodel.CartViewModel
import com.example.e_commerceabb.utils.Constants.EMPTY
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
        setBottomSheet()
        viewModel.getCartProducts()
        observeCartProducts()
        setListeners()
        observePlaceOrder()
        observeDeleteCart()
    }

    private fun observeCartProducts() {
        viewModel.cartProducts.observe(viewLifecycleOwner) {
            binding.progress.visibility = View.GONE
            when (it) {
                is Resource.Success -> {
                    if (it.data?.products?.isEmpty() == true) {
                        binding.emptyTitle.visibility = View.VISIBLE
                    } else {
                        binding.emptyTitle.visibility = View.GONE
                    }
                    cartProductList.clear()
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

    private fun observePlaceOrder() {
        viewModel.orders.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    viewModel.deleteCart()
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

    private fun observeDeleteCart() {
        viewModel.deleted.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.nav_orders)
                    binding.progress.visibility = View.GONE
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
        binding.btnBuyNow.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            val customerId = viewModel.cartProducts.value?.data?.customerId?.id ?: EMPTY
            val email = viewModel.cartProducts.value?.data?.customerId?.email ?: EMPTY
            val products = viewModel.cartProducts.value?.data
            val request = PlaceOrderRequest(
                customerId = customerId,
                email = email,
                products = products,
                status = "shipped"
            )
            viewModel.placeOrders(request)
        }
    }

    private fun setBottomSheet() {
        val tv = TypedValue()
        if (requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            val actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
            val param = binding.checkoutBottomSheet.layoutParams as ConstraintLayout.LayoutParams
            param.setMargins(0, 0, 0, actionBarHeight - 16)
            binding.checkoutBottomSheet.layoutParams = param
        }
    }

    private fun setAdapterData(data: CartProductsResponse) {
        val cartData = data.products
        cartData.forEach { product ->
            val order = Order(
                product.product.id,
                product.product.imageUrls[0],
                product.product.name,
                "US $${product.product.currentPrice}",
                count = product.cartQuantity,
                priceDouble = product.product.currentPrice.toDouble()
            )
            cartProductList.add(order)
        }
        setAdapter()
        adapter.setData(cartProductList)
        setProductPrice()
    }

    private fun setProductPrice() {
        var price = 0.0
        cartProductList.forEach {
            price += it.priceDouble.times(it.count)
        }
        binding.productPrice.text = "US $$price"
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
