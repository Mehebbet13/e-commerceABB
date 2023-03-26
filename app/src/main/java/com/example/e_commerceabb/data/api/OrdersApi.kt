package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.CustomerOrdersResponse
import retrofit2.Response
import retrofit2.http.GET

interface OrdersApi {
    @GET("orders")
    suspend fun getCustomerOrders(): Response<CustomerOrdersResponse>
}
