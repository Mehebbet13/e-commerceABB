package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.FavoriteProducts
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface FavoritesApi {
    @GET("wishlist")
    suspend fun getFavoriteProducts(): Response<FavoriteProducts>

    @PUT("wishlist/{productId}")
    suspend fun addFavoriteProduct(
        @Path("productId") productId: String
    ): Response<FavoriteProducts>

    @DELETE("wishlist/{productId}")
    suspend fun deleteFavoriteProduct(
        @Path("productId") productId: String
    ): Response<FavoriteProducts>
}
