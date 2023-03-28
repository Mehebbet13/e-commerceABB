package com.example.e_commerceabb.presentation.productDetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.repository.CustomerRepositoryImpl
import com.example.e_commerceabb.data.repository.ProductRepositoryImpl
import com.example.e_commerceabb.domain.repository.CustomerRepository
import com.example.e_commerceabb.models.CardResponse
import com.example.e_commerceabb.models.ProductDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepositoryImpl,
    private val repository: CustomerRepositoryImpl
) : ViewModel() {

    private val _productDetail = MutableLiveData<ProductDetailResponse>()
    val productDetail: LiveData<ProductDetailResponse>
        get() = _productDetail

    private val _card = MutableLiveData<CardResponse>()
    val card: LiveData<CardResponse>
        get() = _card


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
                val response = repository.addToCard(itemID)
                response.data.let {
                    _card.postValue(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}