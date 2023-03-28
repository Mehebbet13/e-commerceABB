package com.example.e_commerceabb.data.repository

import com.example.e_commerceabb.data.api.BaseRepository
import com.example.e_commerceabb.data.api.CartProductApi
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.domain.repository.CartProductsRepository
import com.example.e_commerceabb.models.AddProductToCartRequest
import com.example.e_commerceabb.models.CardResponse
import com.example.e_commerceabb.models.CartProductsResponse
import com.example.e_commerceabb.models.DeleteCartResponse
import javax.inject.Inject

class CartProductsRepositoryImpl @Inject constructor(private val cartProductApi: CartProductApi) :
    CartProductsRepository, BaseRepository() {
    override suspend fun getCartProducts(): Resource<CartProductsResponse> {
        return safeApiCall { cartProductApi.getCartProducts() }
    }

    override suspend fun addCartProducts(request: AddProductToCartRequest): Resource<CartProductsResponse> {
        return safeApiCall { cartProductApi.addCartProducts(request) }
    }

    override suspend fun updateCartProducts(request: AddProductToCartRequest): Resource<CartProductsResponse> {
        return safeApiCall { cartProductApi.updateCartProducts(request) }
    }

    override suspend fun addCartProduct(productId: String): Resource<CartProductsResponse> {
        return safeApiCall { cartProductApi.addCartProduct(productId) }
    }

    override suspend fun decreaseProductQuantity(productId: String): Resource<CartProductsResponse> {
        return safeApiCall { cartProductApi.decreaseProductQuantity(productId) }
    }

    override suspend fun deleteProduct(productId: String): Resource<CartProductsResponse> {
        return safeApiCall { cartProductApi.deleteProduct(productId) }
    }

    override suspend fun deleteCart(): Resource<DeleteCartResponse> {
        return safeApiCall { cartProductApi.deleteCart() }
    }

    override suspend fun addToCard(productId: String): Resource<CardResponse> {
        return safeApiCall { cartProductApi.addToCard(productId) }
    }
}
