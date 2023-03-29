package com.example.e_commerceabb.presentation.productDetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.CartProductsRepositoryImpl
import com.example.e_commerceabb.data.repository.FavoriteProductsRepositoryImpl
import com.example.e_commerceabb.data.repository.ProductRepositoryImpl
import com.example.e_commerceabb.models.CardResponse
import com.example.e_commerceabb.models.FavoriteProducts
import com.example.e_commerceabb.models.FilteredResponse
import com.example.e_commerceabb.models.ProductDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepositoryImpl,
    private val cardRepository: CartProductsRepositoryImpl,
    private val favRepository: FavoriteProductsRepositoryImpl
) : ViewModel() {

    private val _productDetail = MutableLiveData<ProductDetailResponse>()
    val productDetail: LiveData<ProductDetailResponse>
        get() = _productDetail

    private val _card = MutableLiveData<CardResponse>()
    val card: LiveData<CardResponse>
        get() = _card

    private val _filteredProduct = MutableLiveData<Resource<FilteredResponse>>()
    val filteredProduct: LiveData<Resource<FilteredResponse>>
        get() = _filteredProduct

    private val _favoriteProducts = MutableLiveData<Resource<FavoriteProducts>>()
    val favoriteProducts: LiveData<Resource<FavoriteProducts>>
        get() = _favoriteProducts

    fun getProductDetails(itemID: String) {
        try {
            viewModelScope.launch {
                val response = productRepository.getProductDetails(itemID)
                response.data.let {
                    _productDetail.postValue(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addToCard(itemID: String) {
        try {
            viewModelScope.launch {
                val response = cardRepository.addToCard(itemID)
                response.data.let {
                    _card.postValue(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getFilteredProduct(name: String) {
        try {
            viewModelScope.launch {
                val response = productRepository.getFilteredProduct(name)
                _filteredProduct.postValue(response)

            }
        } catch (e: Exception) {
            e.printStackTrace()
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