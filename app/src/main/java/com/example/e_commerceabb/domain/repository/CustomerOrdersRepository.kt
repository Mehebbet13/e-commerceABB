package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.*

interface CustomerOrdersRepository {
    suspend fun getCustomersOrders(): Resource<CustomerOrdersResponse>
    suspend fun placeOrders(request: PlaceOrderRequest): Resource<CreateOrderResponse>
    suspend fun addComment(request: AddCommentRequest): Resource<CommentsResponse>
}
