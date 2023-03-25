package com.example.e_commerceabb.presentation.home.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.ListItemHomeRvBinding
import com.example.e_commerceabb.models.HomeCategoryListModel
import com.example.e_commerceabb.presentation.home.adapter.CategoriesAdapter

class CategoriesListViewHolder(
    private val binding: ListItemHomeRvBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: HomeCategoryListModel) {
        val adapter = CategoriesAdapter()
        model.list?.let { adapter.setData(it) }
        with(binding) {
            rvHomeItems.layoutManager =
                GridLayoutManager(itemView.context, 4)
            rvHomeItems.adapter = adapter
        }
    }

    companion object {
        fun create(viewGroup: ViewGroup): CategoriesListViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ListItemHomeRvBinding.inflate(inflater, viewGroup, false)
            return CategoriesListViewHolder(binding)
        }
    }
}