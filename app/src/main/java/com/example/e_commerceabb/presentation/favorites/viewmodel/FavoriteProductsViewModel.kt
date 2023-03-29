package com.example.e_commerceabb.presentation.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.FavoriteProductsRepositoryImpl
import com.example.e_commerceabb.models.FavoriteProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteProductsViewModel @Inject constructor(private val repository: FavoriteProductsRepositoryImpl) :
    ViewModel() {

    private val _favoriteProducts = MutableLiveData<Resource<FavoriteProducts>>()
    val favoriteProducts: LiveData<Resource<FavoriteProducts>>
        get() = _favoriteProducts

    fun getFavoriteProducts() {
        try {
            viewModelScope.launch {
                val response = repository.getFavoriteProducts()
                _favoriteProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addFavoriteProduct(productId: String) {
        try {
            viewModelScope.launch {
                val response = repository.addFavoriteProduct(productId)
                _favoriteProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteFavoriteProduct(productId: String) {
        try {
            viewModelScope.launch {
                val response = repository.deleteFavoriteProduct(productId)
                _favoriteProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
