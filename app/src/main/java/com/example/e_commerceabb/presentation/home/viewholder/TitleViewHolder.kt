package com.example.e_commerceabb.presentation.home.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.ListItemHomeTitleBinding
import com.example.e_commerceabb.models.TitleModel

class TitleViewHolder(private val binding: ListItemHomeTitleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: TitleModel) {
        Log.d("gdfgdfgdfgdfg","pos: holder")
        with(binding) {
            txtTitle.text = "model.title"
            seeAll.text = "model.seeAll"
        }
    }

    companion object {

        fun create(viewGroup: ViewGroup): TitleViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val binding = ListItemHomeTitleBinding.inflate(inflater, viewGroup, false)
            return TitleViewHolder(binding)
        }
    }
}