package com.example.e_commerceabb.presentation.product.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.ListItemHomeProductGroupedBinding
import com.example.e_commerceabb.databinding.ListItemProductBinding
import com.example.e_commerceabb.models.ProductResponse
import com.example.e_commerceabb.utils.BaseAdapter
import com.example.e_commerceabb.utils.Constants.EMPTY
import com.example.e_commerceabb.utils.load

class ProductAdapter(val isGrouped: Boolean) :
    BaseAdapter<ProductResponse, RecyclerView.ViewHolder>() {
    var itemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindingGrouped = ListItemHomeProductGroupedBinding.inflate(inflater, parent, false)
        val binding = ListItemProductBinding.inflate(inflater, parent, false)

        return if (isGrouped) {
            ProductGroupedViewHolder(bindingGrouped)
        } else {
            ProductViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isGrouped) {
            (holder as ProductGroupedViewHolder).bind(list[position])
        } else {
            (holder as ProductViewHolder).bind(list[position])
        }
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke()
        }
    }

    inner class ProductGroupedViewHolder(private val binding: ListItemHomeProductGroupedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponse) {
            with(binding) {
                titleProduct.text = item.name
                subtitleProduct.text = item.description
                discountLabel.text = "-10%"
                discountAmountProduct.text = "${item.previousPrice} ${"$"}"
                amountProduct.text = "${item.previousPrice} ${"$"}"
//                imgProduct.load(R.drawable.rectangle_14)
                imgProduct.load(item.imageUrls?.get(0) ?: EMPTY)
            }
        }
    }

    inner class ProductViewHolder(private val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponse) {
            with(binding) {
                subtitleProduct.text = item.description
                productName.text = item.name
                discountLabel.text = "-10%"
                discountAmountProduct.text = "${item.previousPrice} ${"$"}"
                amountProduct.text = "${item.previousPrice} ${"$"}"
                productImage.load(item.imageUrls?.get(0) ?: EMPTY)
            }
        }
    }
}
