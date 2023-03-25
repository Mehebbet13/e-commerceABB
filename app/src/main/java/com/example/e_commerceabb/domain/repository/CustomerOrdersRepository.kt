package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.CustomerOrdersResponse

interface CustomerOrdersRepository {
    suspend fun getCustomersOrders(): Resource<CustomerOrdersResponse>
}
