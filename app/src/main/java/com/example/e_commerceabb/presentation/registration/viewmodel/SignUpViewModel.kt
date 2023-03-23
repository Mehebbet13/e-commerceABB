package com.example.e_commerceabb.presentation.registration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.CustomerRepositoryImpl
import com.example.e_commerceabb.models.LoginRequest
import com.example.e_commerceabb.models.LoginResponse
import com.example.e_commerceabb.models.NewCustomerRequest
import com.example.e_commerceabb.models.NewCustomerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: CustomerRepositoryImpl) :
    ViewModel() {

    private val _customer = MutableLiveData<Resource<NewCustomerResponse>>()
    val customer: LiveData<Resource<NewCustomerResponse>>
        get() = _customer

    private val _login = MutableLiveData<Resource<LoginResponse>>()
    val login: LiveData<Resource<LoginResponse>>
        get() = _login

    fun createCustomer(request: NewCustomerRequest) {
        try {
            viewModelScope.launch {
                val response = repository.createCustomer(request)
                _customer.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun logIn(request: LoginRequest) {
        try {
            viewModelScope.launch {
                val response = repository.logIntoStore(request)
                _login.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}