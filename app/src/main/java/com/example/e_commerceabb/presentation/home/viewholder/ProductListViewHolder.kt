package com.example.e_commerceabb.presentation.home.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.ListItemHomeRvBinding
import com.example.e_commerceabb.models.HomeProductsListModel
import com.example.e_commerceabb.presentation.home.adapter.HomeProductAdapter

class ProductListViewHolder(
    private val binding: ListItemHomeRvBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: HomeProductsListModel) {
        val adapter = HomeProductAdapter()
        model.list.let { adapter.setData(it) }
        with(binding) {
            seeAll.text = model.title.seeAll
            seeAll.setOnClickListener {
                model.title.onClick()
            }
            txtTitle.text = model.title.title
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