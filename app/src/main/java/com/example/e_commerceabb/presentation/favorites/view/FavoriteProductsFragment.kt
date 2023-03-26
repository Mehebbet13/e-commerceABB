package com.example.e_commerceabb.presentation.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceabb.R
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.databinding.FragmentFavoriteProductsBinding
import com.example.e_commerceabb.models.FavoriteProducts
import com.example.e_commerceabb.models.Order
import com.example.e_commerceabb.presentation.favorites.viewmodel.FavoriteProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteProductsFragment : Fragment(R.layout.fragment_favorite_products) {
    lateinit var binding: FragmentFavoriteProductsBinding
    private val adapter by lazy { FavoritesAdapter() }
    var favorites = mutableListOf<Order>()
    private val viewModel: FavoriteProductsViewModel by viewModels({ this })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteProducts()
        observeFavorites()
        setListeners()
    }

    private fun observeFavorites() {
        viewModel.favoriteProducts.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data -> setAdapterData(data) }
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setAdapterData(data: FavoriteProducts) {
        val favoriteData = data.products
        favoriteData.forEach { product ->
            val order = Order(
                product.id,
                product.imageUrls[0],
                product.name,
                "US $${product.currentPrice}"
            )
            favorites.add(order)
        }
        setAdapter()
        adapter.setData(favorites)
    }

    private fun setAdapter() {
        binding.favoritesRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.favoritesRv.adapter = adapter
        adapter.onFavButtonClick = { productId ->
            viewModel.deleteFavoriteProduct(productId)
        }
    }
}
