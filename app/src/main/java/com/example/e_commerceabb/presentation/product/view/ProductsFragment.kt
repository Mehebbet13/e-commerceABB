package com.example.e_commerceabb.presentation.product.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentProductsBinding
import com.example.e_commerceabb.presentation.product.viewmodel.ProductsViewModel
import com.example.e_commerceabb.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductsBinding
    private var isGrouped: Boolean = true
    private var productAdapter: ProductAdapter? = null

    private val viewModel: ProductsViewModel by viewModels({ this })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCustomerOrders()
        setAdapter()
        binding.groupListIcon.setOnClickListener {
            isGrouped = isGrouped.not()
            setAdapter()
        }
        viewModel.isRefreshing.observe(viewLifecycleOwner) { isRefreshing ->
            binding.progressLayout.root.visibility = if (isRefreshing) View.VISIBLE else View.GONE
        }
    }

    private fun setAdapter() {
        productAdapter = ProductAdapter(isGrouped)
        if (isGrouped) {
            binding.groupListIcon.setImageResource(R.drawable.ic_group_list)
            binding.rvProducts.layoutManager = GridLayoutManager(context, 2)
        } else {
            binding.groupListIcon.setImageResource(R.drawable.ic_grouped)
            binding.rvProducts.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        binding.rvProducts.adapter = productAdapter
        viewModel.products.observe(viewLifecycleOwner) {
            productAdapter?.setData(it.reversed())
            productAdapter?.itemClickListener = {
                val bundle = bundleOf(
                    Constants.ITEM_NO to it[0].itemNo
                )
                findNavController().navigate(
                    R.id.action_productsFragment_to_productDetailFragment,
                    bundle
                )
            }
        }

    }

}