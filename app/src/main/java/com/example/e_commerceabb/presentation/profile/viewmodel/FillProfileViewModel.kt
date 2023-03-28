package com.example.e_commerceabb.presentation.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.CustomerRepositoryImpl
import com.example.e_commerceabb.models.GetCustomerResponse
import com.example.e_commerceabb.models.UpdateCustomerRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FillProfileViewModel @Inject constructor(private val repository: CustomerRepositoryImpl) :
    ViewModel() {

    private val _customer = MutableLiveData<Resource<GetCustomerResponse>>()
    val customer: LiveData<Resource<GetCustomerResponse>>
        get() = _customer

    private val _isAdmin = MutableLiveData<Boolean>()
    val isAdmin: LiveData<Boolean>
        get() = _isAdmin

    fun updateCustomer(request: UpdateCustomerRequest) {
        try {
            viewModelScope.launch {
                val response = repository.updateCustomer(request)
                _customer.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCustomer() {
        try {
            viewModelScope.launch {
                val response = repository.getCustomerData()
                _customer.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setIsAdmin(isAdmin: Boolean) {
        _isAdmin.postValue(isAdmin)
    }
}
