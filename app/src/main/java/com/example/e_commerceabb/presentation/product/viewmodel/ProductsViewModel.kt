package com.example.e_commerceabb.presentation.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.FavoriteProductsRepositoryImpl
import com.example.e_commerceabb.data.repository.ProductRepositoryImpl
import com.example.e_commerceabb.models.FavoriteProducts
import com.example.e_commerceabb.models.ProductResponse
import com.example.e_commerceabb.models.SearchRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductRepositoryImpl,
    private val favRepository: FavoriteProductsRepositoryImpl
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductResponse>>()
    val products: LiveData<List<ProductResponse>>
        get() = _products

    var isRefreshing = MutableLiveData(false)

    private val _favoriteProducts = MutableLiveData<Resource<FavoriteProducts>>()
    val favoriteProducts: LiveData<Resource<FavoriteProducts>>
        get() = _favoriteProducts

    fun getCustomerOrders() {
        try {
            isRefreshing.postValue(true)
            viewModelScope.launch {
                val response = repository.getProducts()
                response.data.let {
                    _products.postValue(it)
                }
            }
            isRefreshing.postValue(false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            when (val response = repository.search(SearchRequest(query))) {
                is Resource.Success -> {
                    response.data?.let {
                        _products.postValue(it)
                    }
                }
            }
        }
    }

    fun addFavoriteProduct(productId: String) {
        try {
            viewModelScope.launch {
                val response = favRepository.addFavoriteProduct(productId)
                _favoriteProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun deleteFavoriteProduct(productId: String) {
        try {
            viewModelScope.launch {
                val response = favRepository.deleteFavoriteProduct(productId)
                _favoriteProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}