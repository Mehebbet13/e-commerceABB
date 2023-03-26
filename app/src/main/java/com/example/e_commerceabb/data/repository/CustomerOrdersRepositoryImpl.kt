package com.example.e_commerceabb.data.repository

import com.example.e_commerceabb.data.api.BaseRepository
import com.example.e_commerceabb.data.api.OrdersApi
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.domain.repository.CustomerOrdersRepository
import com.example.e_commerceabb.models.CustomerOrdersResponse
import javax.inject.Inject

class CustomerOrdersRepositoryImpl @Inject constructor(private val ordersApi: OrdersApi) :
    CustomerOrdersRepository, BaseRepository() {
    override suspend fun getCustomersOrders(): Resource<CustomerOrdersResponse> {
        return safeApiCall { ordersApi.getCustomerOrders() }
    }
}
