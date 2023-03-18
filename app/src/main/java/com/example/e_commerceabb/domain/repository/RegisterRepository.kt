package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.RegisterApi
import com.example.e_commerceabb.data.repository.RegisterRepository
import com.example.e_commerceabb.models.NewCustomerRequest
import com.example.e_commerceabb.models.NewCustomerResponse
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerAPI: RegisterApi) :
    RegisterRepository {
    override suspend fun createCustomer(request: NewCustomerRequest): NewCustomerResponse {
        return registerAPI.createNewCustomer(request)
    }
}
