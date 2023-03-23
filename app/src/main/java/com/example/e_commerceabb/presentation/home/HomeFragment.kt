package com.example.e_commerceabb.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentHomeBinding
import com.example.e_commerceabb.models.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val homeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HomeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = homeAdapter
        homeAdapter.setData(
            listOf(
                TitleModel("Sevilən yeməklərimiz"),
                HomeCategoryListModel(
                    arrayListOf(
                        HomeCategoryModel(
                            "Burger",
                            R.drawable.rectangle_14
                        ),
                        HomeCategoryModel(
                            "Pizza",
                            R.drawable.rectangle_14
                        ),
                        HomeCategoryModel(
                            "Pizza",
                            R.drawable.rectangle_14
                        ),
                        HomeCategoryModel(
                            "Pizza",
                            R.drawable.rectangle_14
                        ),    HomeCategoryModel(
                            "Pizza",
                            R.drawable.rectangle_14
                        ),    HomeCategoryModel(
                            "Pizza",
                            R.drawable.rectangle_14
                        ),
                        HomeCategoryModel(
                            "Pizza",
                            R.drawable.rectangle_14
                        ),
                    )
                ),
                TitleModel("Sevilən yeməklərimiz","See all"),
                HomeProductsListModel(
                    arrayListOf(
                        HomeProductsModel(
                            R.drawable.rectangle_14,
                            "-10%",
                            "Iphone 13pro max",
                            "Description",
                            "600$",
                            "544$"
                        ),     HomeProductsModel(
                            R.drawable.rectangle_14,
                            "-10%",
                            "Iphone 13pro max",
                            "Description",
                            "600$",
                            "544$"
                        ),     HomeProductsModel(
                            R.drawable.rectangle_14,
                            "-10%",
                            "Iphone 13pro max",
                            "Description",
                            "600$",
                            "544$"
                        ),
                        HomeProductsModel(
                            R.drawable.rectangle_14,
                            "-10%",
                            "Iphone 13pro max",
                            "Description",
                            "600$",
                            "544$"
                        ),

                    )
                ),
            )
        )
    }

    companion object {
    }
}