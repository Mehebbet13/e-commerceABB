package com.example.e_commerceabb.presentation.admin.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.ProductRepositoryImpl
import com.example.e_commerceabb.models.AddProductRequest
import com.example.e_commerceabb.models.FilteredResponse
import com.example.e_commerceabb.models.ProductResponse
import com.example.e_commerceabb.utils.Jwt.getUserId
import com.example.e_commerceabb.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val repository: ProductRepositoryImpl) :
    ViewModel() {

    @Inject
    lateinit var tokenManager: TokenManager

    private val _addedProduct = MutableLiveData<Resource<ProductResponse>>()
    val addedProduct: LiveData<Resource<ProductResponse>>
        get() = _addedProduct

    private val _addedProducts = MutableLiveData<Resource<FilteredResponse>>()
    val addedProducts: LiveData<Resource<FilteredResponse>>
        get() = _addedProducts

    fun addProduct(request: AddProductRequest) {
        try {
            viewModelScope.launch {
                val response = repository.addProduct(request)
                _addedProduct.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAddedProducts() {
        val token = tokenManager.getToken()
        val userId = token?.let { getUserId(it) }
        try {
            viewModelScope.launch {
                val response = userId?.let { repository.getFilteredProduct(userId) }
                _addedProducts.postValue(response!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
