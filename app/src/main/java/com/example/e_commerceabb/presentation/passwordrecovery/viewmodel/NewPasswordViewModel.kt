package com.example.e_commerceabb.presentation.passwordrecovery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.data.repository.CustomerRepositoryImpl
import com.example.e_commerceabb.models.UpdatePasswordRequest
import com.example.e_commerceabb.models.UpdatePasswordResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(private val repository: CustomerRepositoryImpl) :
    ViewModel() {

    private val _newPassword = MutableLiveData<Resource<UpdatePasswordResponse>>()
    val newPassword: LiveData<Resource<UpdatePasswordResponse>>
        get() = _newPassword

    fun updatePassword(request: UpdatePasswordRequest) {
        try {
            viewModelScope.launch {
                val response = repository.updateCustomerPassword(request)
                _newPassword.postValue(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
