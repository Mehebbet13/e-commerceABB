package com.example.e_commerceabb.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceabb.R
import com.example.e_commerceabb.databinding.FragmentHomeBinding
import com.example.e_commerceabb.presentation.home.adapter.HomeAdapter
import com.example.e_commerceabb.presentation.home.viewmodel.HomeViewModel
import com.example.e_commerceabb.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        HomeAdapter()
    }
    private val viewModel: HomeViewModel by viewModels({ this })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = homeAdapter
        viewModel.getProducts()
        viewModel.getCategory()
        binding.imgFav.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_favoriteProductsFragment)
        }
        binding.searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                viewModel.search(query)
                return false
            }
        })
        viewModel.productsList.observe(viewLifecycleOwner) { resource ->
            homeAdapter.setList(resource)
        }
        viewModel.catalog.observe(viewLifecycleOwner) { resource ->
            homeAdapter.setList(resource)
        }
        viewModel.mapper.onSeeAllClick = {
            findNavController().navigate(R.id.action_homeFragment_to_productsFragment)
        }
        viewModel.mapper.onFavIconCLick = {
            viewModel.addFavoriteProduct(it)
        }
        viewModel.mapper.onItemCLick = { itemNo ->
            val bundle = bundleOf(
                Constants.ITEM_NO to itemNo
            )
            findNavController().navigate(
                R.id.action_homeFragment_to_productDetailFragment,
                bundle
            )
        }
    }
}