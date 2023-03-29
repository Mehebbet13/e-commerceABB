package com.example.e_commerceabb.presentation.productReview.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.ListItemReviewBinding
import com.example.e_commerceabb.models.CommentsResponse
import com.example.e_commerceabb.utils.BaseAdapter

class ReviewAdapter() :
    BaseAdapter<CommentsResponse, RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemReviewBinding.inflate(inflater, parent, false)
        return ProductGroupedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductGroupedViewHolder).bind(list[position])
    }

    inner class ProductGroupedViewHolder(private val binding: ListItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentsResponse) {
            with(binding) {
                reviewTitle.text = "${item.customer.firstName}${item.customer.lastName}"
                reviewSubTitle.text =item.content
                reviewDate.text=item.customer.date
            }
        }
    }
}
