package com.example.e_commerceabb.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.models.HomeCategoryListModel
import com.example.e_commerceabb.models.HomeProductsListModel
import com.example.e_commerceabb.models.HomeRVItem
import com.example.e_commerceabb.models.TitleModel
import com.example.e_commerceabb.presentation.home.viewholder.CategoriesListViewHolder
import com.example.e_commerceabb.presentation.home.viewholder.ProductListViewHolder
import com.example.e_commerceabb.presentation.home.viewholder.TitleViewHolder
import com.example.e_commerceabb.utils.BaseAdapter

class HomeAdapter : BaseAdapter<HomeRVItem, RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is TitleModel -> TITLE_VIEW_TYPE
            is HomeCategoryListModel -> CATEGORIES_VIEW_TYPE
            is HomeProductsListModel -> PRODUCT_VIEW_TYPE
            else -> {
                TITLE_VIEW_TYPE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE_VIEW_TYPE -> TitleViewHolder.create(parent)
            CATEGORIES_VIEW_TYPE -> CategoriesListViewHolder.create(parent)
            PRODUCT_VIEW_TYPE -> ProductListViewHolder.create(parent)
            else -> {
                TitleViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = list[position]) {
            is TitleModel -> (holder as TitleViewHolder).bind(item)
            is HomeCategoryListModel -> (holder as CategoriesListViewHolder).bind(item)
            is HomeProductsListModel -> (holder as ProductListViewHolder).bind(item)
        }
    }

    companion object {
        const val TITLE_VIEW_TYPE = 0
        const val CATEGORIES_VIEW_TYPE = 1
        const val PRODUCT_VIEW_TYPE = 2
    }
}