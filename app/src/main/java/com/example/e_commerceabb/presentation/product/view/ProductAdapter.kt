package com.example.e_commerceabb.presentation.product.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.ListItemHomeProductGroupedBinding
import com.example.e_commerceabb.databinding.ListItemProductBinding
import com.example.e_commerceabb.models.ProductResponse
import com.example.e_commerceabb.utils.BaseAdapter
import com.example.e_commerceabb.utils.Constants.EMPTY
import com.example.e_commerceabb.utils.load

class ProductAdapter(val isGrouped: Boolean, val isFaved: Boolean) :
    BaseAdapter<ProductResponse, RecyclerView.ViewHolder>() {
    var itemClickListener: ((itemNo: String) -> Unit)? = null
    var itemFavIconListener: ((id: String) -> Unit)? = null

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
    }

    inner class ProductGroupedViewHolder(private val binding: ListItemHomeProductGroupedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponse) {
            with(binding) {
                titleProduct.text = item.name
                subtitleProduct.text = item.description
                discountLabel.text = "-10%"
                discountAmountProduct.text = "${item.currentPrice} $"
                if (item.previousPrice == null) {
                    amountProduct.visibility = View.GONE
                } else {
                    amountProduct.visibility = View.VISIBLE
                    amountProduct.text = "${item.previousPrice} $"
                }
                imgProduct.load(item.imageUrls?.getOrNull(0) ?: EMPTY)
                productRoot.setOnClickListener {
                    item.itemNo.let { it1 -> itemClickListener?.invoke(it1) }
                }
                favIcon.setOnClickListener {
                    itemFavIconListener?.invoke(item.id ?: EMPTY)
                    favIcon.setImageResource(R.drawable.ic_heart_fill)
                }
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
                discountAmountProduct.text = "${item.previousPrice} $"
                if (item.previousPrice == null) {
                    amountProduct.visibility = View.GONE
                } else {
                    amountProduct.visibility = View.VISIBLE
                    amountProduct.text = "${item.previousPrice} $"
                }
                productImage.load(item.imageUrls?.getOrNull(0) ?: EMPTY)
                productRoot.setOnClickListener {
                    item.itemNo.let { it1 -> itemClickListener?.invoke(it1) }
                }
                favIcon.setOnClickListener {
                    itemFavIconListener?.invoke(item.id ?: EMPTY)
                    favIcon.setImageResource(R.drawable.ic_heart_fill)
                }
            }
        }
    }
}
