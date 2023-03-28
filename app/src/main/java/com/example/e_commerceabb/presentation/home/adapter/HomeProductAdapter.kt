package com.example.e_commerceabb.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.e_commerceabb.databinding.ListItemHomeProductBinding
import com.example.e_commerceabb.models.HomeProductsModel
import com.example.e_commerceabb.utils.BaseAdapter
import com.example.e_commerceabb.utils.load

class HomeProductAdapter : BaseAdapter<HomeProductsModel, ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemHomeProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ProductViewHolder).bind(list[position])
    }

    inner class ProductViewHolder(private val binding: ListItemHomeProductBinding) :
        ViewHolder(binding.root) {
        fun bind(item: HomeProductsModel) {
            with(binding) {
                titleProduct.text = item.title
                subtitleProduct.text = item.subtitle
                discountLabel.text = item.discountLAbel
                discountAmountProduct.text = item.discountAmount
                amountProduct.text = item.amount
                productImage.load(item.image)
            }
        }
    }
}