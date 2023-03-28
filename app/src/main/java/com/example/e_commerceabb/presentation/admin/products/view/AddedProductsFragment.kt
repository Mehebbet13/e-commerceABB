package com.example.e_commerceabb.presentation.admin.products.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentAddedProductsBinding

class AddedProductsFragment : Fragment(R.layout.fragment_added_products) {
    lateinit var binding: FragmentAddedProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddedProductsBinding.inflate(layoutInflater)
        return binding.root
    }
}
