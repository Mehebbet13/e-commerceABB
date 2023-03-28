package com.example.e_commerceabb.presentation.productDetails.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.ListItemHomeProductBinding
import com.example.e_commerceabb.databinding.ListItemProductBinding
import com.example.e_commerceabb.models.ProductResponse
import com.example.e_commerceabb.utils.BaseAdapter
import com.example.e_commerceabb.utils.load

class ProductDetailAdapter() :
    BaseAdapter<ProductResponse, RecyclerView.ViewHolder>() {
    var itemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemHomeProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).bind(list[position])
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke()
        }
    }

    inner class ProductGroupedViewHolder(private val binding: ListItemHomeProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponse) {
            with(binding) {
                titleProduct.text = item.name
                subtitleProduct.text = item.description
                discountLabel.text = "-10%"
                discountAmountProduct.text = "${item.previousPrice} ${"$"}"
                amountProduct.text = "${item.previousPrice} ${"$"}"
                imgProduct.load(R.drawable.rectangle_14)
                // imgProduct.load(item.)
            }
        }
    }

    inner class ProductViewHolder(private val binding: ListItemHomeProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponse) {
            with(binding) {
                subtitleProduct.text = item.description
                titleProduct.text = item.name
                discountLabel.text = "-10%"
                discountAmountProduct.text = "${item.previousPrice} ${"$"}"
                amountProduct.text = "${item.previousPrice} ${"$"}"
                imgProduct.load(R.drawable.rectangle_14)
            }
        }
    }
}