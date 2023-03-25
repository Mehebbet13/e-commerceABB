package com.example.e_commerceabb.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceabb.databinding.FragmentHomeBinding
import com.example.e_commerceabb.models.HomeRVItem
import com.example.e_commerceabb.presentation.home.adapter.HomeAdapter
import com.example.e_commerceabb.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HomeAdapter()
    }
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = homeAdapter
        viewModel.getProducts()
        viewModel.productsList.observe(viewLifecycleOwner) { resource ->
            Log.d("fdssdfsdfsdf",resource.toString())
            homeAdapter.setData(resource)
        }
//        homeAdapter.setData(
//            listOf(
//                TitleModel("Sevilən yeməklərimiz"),
//                HomeCategoryListModel(
//                    arrayListOf(
//                        HomeCategoryModel(
//                            "Burger",
//                            R.drawable.rectangle_14
//                        ),
//                        HomeCategoryModel(
//                            "Pizza",
//                            R.drawable.rectangle_14
//                        ),
//                        HomeCategoryModel(
//                            "Pizza",
//                            R.drawable.rectangle_14
//                        ),
//                        HomeCategoryModel(
//                            "Pizza",
//                            R.drawable.rectangle_14
//                        ),
//                        HomeCategoryModel(
//                            "Pizza",
//                            R.drawable.rectangle_14
//                        ),
//                        HomeCategoryModel(
//                            "Pizza",
//                            R.drawable.rectangle_14
//                        ),
//                        HomeCategoryModel(
//                            "Pizza",
//                            R.drawable.rectangle_14
//                        ),
//                    )
//                ),
//                TitleModel("Products", "See all"),
//            )
//        )
    }


    companion object {
    }
}