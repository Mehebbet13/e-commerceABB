package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.AddProductToCartRequest
import com.example.e_commerceabb.models.CardResponse
import com.example.e_commerceabb.models.CartProductsResponse

interface CartProductsRepository {
    suspend fun getCartProducts(): Resource<CartProductsResponse>
    suspend fun addCartProducts(request: AddProductToCartRequest): Resource<CartProductsResponse>
    suspend fun updateCartProducts(request: AddProductToCartRequest): Resource<CartProductsResponse>
    suspend fun addCartProduct(productId: String): Resource<CartProductsResponse>
    suspend fun decreaseProductQuantity(productId: String): Resource<CartProductsResponse>
    suspend fun deleteProduct(productId: String): Resource<CartProductsResponse>
    suspend fun deleteCart(): Resource<String>
    suspend fun addToCard(itemNo: String): Resource<CardResponse>
}