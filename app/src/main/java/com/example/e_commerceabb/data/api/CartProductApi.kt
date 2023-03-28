package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.AddProductToCartRequest
import com.example.e_commerceabb.models.CardResponse
import com.example.e_commerceabb.models.CartProductsResponse
import retrofit2.Response
import retrofit2.http.*

interface CartProductApi {
    @GET("cart")
    suspend fun getCartProducts(): Response<CartProductsResponse>

    @POST("cart")
    suspend fun addCartProducts(
        @Body request: AddProductToCartRequest
    ): Response<CartProductsResponse>

    @PUT("cart")
    suspend fun updateCartProducts(
        @Body request: AddProductToCartRequest
    ): Response<CartProductsResponse>

    @PUT("cart/{productId}")
    suspend fun addCartProduct(
        @Path("productId") productId: String
    ): Response<CartProductsResponse>

    @DELETE("cart/product/{productId}")
    suspend fun decreaseProductQuantity(
        @Path("productId") productId: String
    ): Response<CartProductsResponse>

    @DELETE("cart/{productId}")
    suspend fun deleteProduct(
        @Path("productId") productId: String
    ): Response<CartProductsResponse>

    @DELETE("cart")
    suspend fun deleteCart(): Response<String>

    @PUT("cart/{productId}")
    suspend fun addToCard(
        @Path("productId") productId: String
    ): Response<CardResponse>
}
