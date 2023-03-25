package com.example.e_commerceabb.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.e_commerceabb.databinding.ListItemHomeCategoryBinding
import com.example.e_commerceabb.models.HomeCategoryModel
import com.example.e_commerceabb.utils.BaseAdapter
import com.example.e_commerceabb.utils.load

class CategoriesAdapter : BaseAdapter<HomeCategoryModel, ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemHomeCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bind(list[position])
    }

    inner class CategoryViewHolder(private val binding: ListItemHomeCategoryBinding) :
        ViewHolder(binding.root) {
        fun bind(item: HomeCategoryModel) {
            with(binding) {
                titleCategory.text = item.title
                imgCategory.load(item.image)
            }
        }
    }
}