package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface CustomerApi {
    @POST("customers")
    suspend fun createNewCustomer(
        @Body request: NewCustomerRequest
    ): Response<NewCustomerResponse>

    @POST("customers/login")
    suspend fun logIntoStore(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("customers/customer")
    suspend fun getCustomerData(): GetCustomerResponse

    @PUT("customers")
    suspend fun updateCustomer(
        @Body request: UpdateCustomerRequest
    ): GetCustomerResponse

    @PUT("customers/password")
    suspend fun updateCustomerPassword(
        @Body request: UpdatePasswordRequest
    ): Response<UpdatePasswordResponse>
}