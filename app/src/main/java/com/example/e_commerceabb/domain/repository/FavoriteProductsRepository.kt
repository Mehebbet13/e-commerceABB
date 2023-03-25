package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.FavoriteProducts

interface FavoriteProductsRepository {
    suspend fun getFavoriteProducts(): Resource<FavoriteProducts>
    suspend fun addFavoriteProduct(productId: String): Resource<FavoriteProducts>
    suspend fun deleteFavoriteProduct(productId: String): Resource<FavoriteProducts>
}
