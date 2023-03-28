package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.CreateOrderResponse
import com.example.e_commerceabb.models.CustomerOrdersResponse
import com.example.e_commerceabb.models.PlaceOrderRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrdersApi {
    @GET("orders")
    suspend fun getCustomerOrders(): Response<CustomerOrdersResponse>

    @POST("orders")
    suspend fun placeOrders(
        @Body request: PlaceOrderRequest
    ): Response<CreateOrderResponse>
}
