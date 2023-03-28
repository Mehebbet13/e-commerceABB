package com.example.e_commerceabb.data.repository

import com.example.e_commerceabb.data.api.BaseRepository
import com.example.e_commerceabb.data.api.OrdersApi
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.domain.repository.CustomerOrdersRepository
import com.example.e_commerceabb.models.CreateOrderResponse
import com.example.e_commerceabb.models.CustomerOrdersResponse
import com.example.e_commerceabb.models.PlaceOrderRequest
import javax.inject.Inject

class CustomerOrdersRepositoryImpl @Inject constructor(private val ordersApi: OrdersApi) :
    CustomerOrdersRepository, BaseRepository() {
    override suspend fun getCustomersOrders(): Resource<CustomerOrdersResponse> {
        return safeApiCall { ordersApi.getCustomerOrders() }
    }

    override suspend fun placeOrders(request: PlaceOrderRequest): Resource<CreateOrderResponse> {
        return safeApiCall { ordersApi.placeOrders(request) }
    }
}
