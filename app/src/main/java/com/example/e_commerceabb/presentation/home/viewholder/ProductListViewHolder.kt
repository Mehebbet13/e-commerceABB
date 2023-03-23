package com.example.e_commerceabb.presentation.home.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.ListItemHomeRvBinding
import com.example.e_commerceabb.models.HomeCategoryListModel
import com.example.e_commerceabb.models.HomeProductsListModel
import com.example.e_commerceabb.models.HomeProductsModel
import com.example.e_commerceabb.presentation.home.CategoriesAdapter
import com.example.e_commerceabb.presentation.home.ProductAdapter

class ProductListViewHolder(
    private val binding: ListItemHomeRvBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: HomeProductsListModel) {
        val adapter = ProductAdapter()
        model.list?.let { adapter.setData(it) }
        with(binding) {
            rvHomeItems.layoutManager =
                LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            rvHomeItems.adapter = adapter
        }
    }

    companion object {
        fun create(viewGroup: ViewGroup): ProductListViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ListItemHomeRvBinding.inflate(inflater, viewGroup, false)
            return ProductListViewHolder(binding)
        }
    }
}