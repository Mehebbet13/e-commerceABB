package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.*
import retrofit2.Response

interface CustomerRepository {
    suspend fun createCustomer(request: NewCustomerRequest): Resource<NewCustomerResponse>
    suspend fun logIntoStore(request: LoginRequest): Resource<LoginResponse>
    suspend fun getCustomerData(): GetCustomerResponse
    suspend fun updateCustomer(request: UpdateCustomerRequest): GetCustomerResponse
    suspend fun updateCustomerPassword(request: UpdatePasswordRequest): Resource<UpdatePasswordResponse>
    suspend fun products(request: NewProductRequest): Resource<ProductResponse>
    suspend fun getProducts(): Resource<ArrayList<ProductResponse>>
}
