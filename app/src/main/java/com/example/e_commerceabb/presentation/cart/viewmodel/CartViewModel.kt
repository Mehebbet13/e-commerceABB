package com.example.e_commerceabb.presentation.cart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.CartProductsRepositoryImpl
import com.example.e_commerceabb.models.AddProductToCartRequest
import com.example.e_commerceabb.models.CartProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartProductsRepositoryImpl) :
    ViewModel() {

    private val _cartProducts = MutableLiveData<Resource<CartProductsResponse>>()
    val cartProducts: LiveData<Resource<CartProductsResponse>>
        get() = _cartProducts

    // all products added to cart
    fun getCartProducts() {
        try {
            viewModelScope.launch {
                val response = repository.getCartProducts()
                _cartProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // For creating new cart
    fun addCartProducts(request: AddProductToCartRequest) {
        try {
            viewModelScope.launch {
                val response = repository.addCartProducts(request)
                _cartProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // for editing data of existing cart
    fun updateCartProducts(request: AddProductToCartRequest) {
        try {
            viewModelScope.launch {
                val response = repository.updateCartProducts(request)
                _cartProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Add one product to cart
    fun addCartProduct(productId: String) {
        try {
            viewModelScope.launch {
                val response = repository.addCartProduct(productId)
                _cartProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun decreaseProductQuantity(productId: String) {
        try {
            viewModelScope.launch {
                val response = repository.decreaseProductQuantity(productId)
                _cartProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteProduct(productId: String) {
        try {
            viewModelScope.launch {
                val response = repository.deleteProduct(productId)
                _cartProducts.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun deleteCart() {
        try {
            viewModelScope.launch {
                repository.deleteCart()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
