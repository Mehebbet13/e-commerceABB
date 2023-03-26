package com.example.e_commerceabb.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.models.HomeCategoryListModel
import com.example.e_commerceabb.models.HomeProductsListModel
import com.example.e_commerceabb.models.HomeRVItem
import com.example.e_commerceabb.presentation.home.viewholder.CategoriesListViewHolder
import com.example.e_commerceabb.presentation.home.viewholder.ProductListViewHolder
import com.example.e_commerceabb.utils.BaseAdapter

class HomeAdapter : BaseAdapter<HomeRVItem, RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is HomeCategoryListModel -> CATEGORIES_VIEW_TYPE
            is HomeProductsListModel -> PRODUCT_VIEW_TYPE
            else -> {
                CATEGORIES_VIEW_TYPE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CATEGORIES_VIEW_TYPE -> CategoriesListViewHolder.create(parent)
            PRODUCT_VIEW_TYPE -> ProductListViewHolder.create(parent)
            else -> {
                CategoriesListViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = list[position]) {
            is HomeCategoryListModel -> (holder as CategoriesListViewHolder).bind(item)
            is HomeProductsListModel -> (holder as ProductListViewHolder).bind(item)
        }
    }

    companion object {
        const val CATEGORIES_VIEW_TYPE = 0
        const val PRODUCT_VIEW_TYPE = 1
    }
}