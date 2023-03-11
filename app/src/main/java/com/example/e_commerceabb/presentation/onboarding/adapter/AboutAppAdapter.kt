package com.example.e_commerceabb.presentation.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceabb.databinding.AboutAppStepBinding
import com.example.e_commerceabb.models.AboutAppData

class AboutAppAdapter(private val data: ArrayList<AboutAppData>) :
    RecyclerView.Adapter<AboutAppAdapter.FAQAnswersHolder>() {
    inner class FAQAnswersHolder(private val binding: AboutAppStepBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(stepData: AboutAppData) {
            binding.stepImage.setBackgroundResource(stepData.image)
            binding.stepTitle.text = stepData.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQAnswersHolder {
        return FAQAnswersHolder(
            AboutAppStepBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FAQAnswersHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
