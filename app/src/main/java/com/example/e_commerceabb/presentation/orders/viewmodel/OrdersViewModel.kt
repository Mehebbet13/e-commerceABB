package com.example.e_commerceabb.presentation.orders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.CustomerOrdersRepositoryImpl
import com.example.e_commerceabb.models.CustomerOrdersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val repository: CustomerOrdersRepositoryImpl) :
    ViewModel() {

    private val _orders = MutableLiveData<Resource<CustomerOrdersResponse>>()
    val orders: LiveData<Resource<CustomerOrdersResponse>>
        get() = _orders

    fun getCustomerOrders() {
        try {
            viewModelScope.launch {
                val response = repository.getCustomersOrders()
                _orders.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
