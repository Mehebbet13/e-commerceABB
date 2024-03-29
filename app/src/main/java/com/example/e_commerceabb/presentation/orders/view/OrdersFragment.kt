package com.example.e_commerceabb.presentation.orders.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentOrdersBinding
import com.example.e_commerceabb.dialogs.CustomDialogFragment
import com.example.e_commerceabb.dialogs.LeaveReviewDialogFragment
import com.example.e_commerceabb.models.AddCommentRequest
import com.example.e_commerceabb.models.CustomerOrdersResponse
import com.example.e_commerceabb.models.Order
import com.example.e_commerceabb.presentation.orders.viewmodel.OrdersViewModel
import com.example.e_commerceabb.utils.Constants.EMPTY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment(R.layout.fragment_orders) {
    lateinit var binding: FragmentOrdersBinding
    private var openDropDown = true
    private val viewModel: OrdersViewModel by viewModels({ this })
    private val adapter by lazy { OrdersAdapter() }
    private val adapter1 by lazy { OrdersAdapter() }
    private val adapter3 by lazy { OrdersAdapter() }
    private var ongoingOrdersList = mutableListOf<Order>()
    private var completedOrdersList = mutableListOf<Order>()
    private var cancelledOrdersList = mutableListOf<Order>()
    private var dialog: LeaveReviewDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleDropDowns()
        viewModel.getCustomerOrders()
        observeGetCustomerOrder()
        handleFavoritesButton()
        setListeners()
    }

    private fun setAdapterData(data: CustomerOrdersResponse) {
        ongoingOrdersList.clear()
        completedOrdersList.clear()
        cancelledOrdersList.clear()
        val completedData = data.filter { it.status == "shipped" && !it.canceled }
        completedData.forEach {
            it.products.forEach { product ->
                val order = Order(
                    product.id,
                    product.product.imageUrls.getOrNull(0) ?: EMPTY,
                    product.product.name ?: EMPTY,
                    "US $${product.product.currentPrice}",
                    getString(R.string.leave_review),
                    getString(R.string.completed)
                )
                completedOrdersList.add(order)
            }
        }
        setCompletedProductsAdapter()
        adapter3.setData(completedOrdersList)

        val ongoingData = data.filter { it.status == "not shipped" && !it.canceled }
        ongoingData.forEach {
            it.products.forEach { product ->
                val order = Order(
                    product.id,
                    product.product.imageUrls[0],
                    product.product.name ?: EMPTY,
                    "US $${product.product.currentPrice}",
                    getString(R.string.track_order),
                    getString(R.string.in_delivery)
                )
                ongoingOrdersList.add(order)
            }
        }
        setOngoingProductsAdapter()
        adapter.setData(ongoingOrdersList)

        val cancelledData = data.filter { it.canceled }
        cancelledData.forEach {
            it.products.forEach { product ->
                val cancelledOrder = Order(
                    product.id,
                    product.product.imageUrls[0],
                    product.product.name ?: EMPTY,
                    "US $${product.product.currentPrice}",
                    getString(R.string.buy_again),
                    getString(R.string.cancelled)
                )
                cancelledOrdersList.add(cancelledOrder)
            }
        }
        setCancelledProductsAdapter()
        adapter1.setData(cancelledOrdersList)
    }

    private fun observeGetCustomerOrder() {
        viewModel.orders.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val data = (it.data as CustomerOrdersResponse)
                    setAdapterData(data)
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

    private fun setOngoingProductsAdapter() {
        binding.ongoingProductsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.ongoingProductsRv.adapter = adapter
    }

    private fun setCompletedProductsAdapter() {
        binding.completedProductsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.completedProductsRv.adapter = adapter3

        adapter3.onButtonClick = {
            showReviewDialog(it)
        }
    }

    private fun showReviewDialog(order: Order) {
        val dialogBuilder = LeaveReviewDialogFragment.Builder()
            .image(order.imageUrl)
            .labelText(order.name)
            .descriptionText(order.price)

        dialog = dialogBuilder.build()

        dialog?.submitListener = {
            val comment = AddCommentRequest(
                it,
                order.id
            )
            viewModel.addComment(comment)
        }
        dialog?.show(parentFragmentManager, CustomDialogFragment::class.java.canonicalName)
    }

    private fun setCancelledProductsAdapter() {
        binding.cancelledProductsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.cancelledProductsRv.adapter = adapter1

        adapter.onButtonClick = {
            // can be handle here
        }
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun handleDropDowns() {
        binding.apply {
            ongoing.setOnClickListener {
                ongoingRightArrow.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        if (openDropDown) R.drawable.ic_arrow_down else R.drawable.ic_arrow_right
                    )
                )
                if (ongoingOrdersList.isEmpty()) {
                    ongoingOrders.isVisible = openDropDown
                } else {
                    ongoingProductsRv.isVisible = openDropDown
                }
                openDropDown = !openDropDown
            }
            completed.setOnClickListener {
                completedRightArrow.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        if (openDropDown) R.drawable.ic_arrow_down else R.drawable.ic_arrow_right
                    )
                )
                if (completedOrdersList.isEmpty()) {
                    completedOrders.isVisible = openDropDown
                } else {
                    completedProductsRv.isVisible = openDropDown
                }
                openDropDown = !openDropDown
            }
            cancelled.setOnClickListener {
                cancelledRightArrow.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        if (openDropDown) R.drawable.ic_arrow_down else R.drawable.ic_arrow_right
                    )
                )
                if (cancelledOrdersList.isEmpty()) {
                    cancelledOrders.isVisible = openDropDown
                } else {
                    cancelledProductsRv.isVisible = openDropDown
                }
                openDropDown = !openDropDown
            }
        }
    }

    private fun handleFavoritesButton() {
        binding.btnWishList.setOnClickListener {
            findNavController().navigate(R.id.action_ordersFragment_to_favoriteProductsFragment)
        }
    }
}
