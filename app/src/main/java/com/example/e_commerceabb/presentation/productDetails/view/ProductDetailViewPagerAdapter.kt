package com.example.e_commerceabb.presentation.productDetails.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.ProductDetailPagerListBinding
import com.example.e_commerceabb.models.ProductDetailPagerData

class ProductDetailViewPagerAdapter(private val data: ArrayList<ProductDetailPagerData>) :
    RecyclerView.Adapter<ProductDetailViewPagerAdapter.ProductDetailPagerHolder>() {
    inner class ProductDetailPagerHolder(private val binding: ProductDetailPagerListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(stepData: ProductDetailPagerData) {
            binding.productImage.setBackgroundResource(stepData.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailPagerHolder {
        return ProductDetailPagerHolder(
            ProductDetailPagerListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductDetailPagerHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
