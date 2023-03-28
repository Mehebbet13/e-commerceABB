package com.example.e_commerceabb.presentation.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.repository.CustomerRepositoryImpl
import com.example.e_commerceabb.models.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: CustomerRepositoryImpl
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductResponse>>()
    val products: LiveData<List<ProductResponse>>
        get() = _products

    var isRefreshing = MutableLiveData(false)

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

}