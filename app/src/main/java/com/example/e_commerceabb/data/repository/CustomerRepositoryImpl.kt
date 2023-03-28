package com.example.e_commerceabb.data.repository

import com.example.e_commerceabb.data.api.BaseRepository
import com.example.e_commerceabb.data.api.CustomerApi
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.domain.repository.CustomerRepository
import com.example.e_commerceabb.models.*
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(private val customerApi: CustomerApi) :
    CustomerRepository, BaseRepository() {
    override suspend fun createCustomer(request: NewCustomerRequest): Resource<NewCustomerResponse> {
        return safeApiCall { customerApi.createNewCustomer(request) }
    }

    override suspend fun logIntoStore(request: LoginRequest): Resource<LoginResponse> {
        return safeApiCall { customerApi.logIntoStore(request) }
    }

    override suspend fun getCustomerData(): Resource<GetCustomerResponse> {
        return safeApiCall { customerApi.getCustomerData() }
    }

    override suspend fun updateCustomer(request: UpdateCustomerRequest): Resource<GetCustomerResponse> {
        return safeApiCall { customerApi.updateCustomer(request) }
    }

    override suspend fun updateCustomerPassword(request: UpdatePasswordRequest): Resource<UpdatePasswordResponse> {
        return safeApiCall { customerApi.updateCustomerPassword(request) }
    }

    override suspend fun products(request: NewProductRequest): Resource<ProductResponse> {
        return customerApi.products(request)
    }

    override suspend fun addToCard(productId: String): Resource<CardResponse> {
        return safeApiCall { customerApi.addToCard(productId) }
    }
}
