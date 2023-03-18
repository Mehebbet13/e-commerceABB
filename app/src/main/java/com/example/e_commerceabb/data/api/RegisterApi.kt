package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.NewCustomerRequest
import com.example.e_commerceabb.models.NewCustomerResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("/customers")
    suspend fun createNewCustomer(
        @Body request: NewCustomerRequest
    ): NewCustomerResponse
}
