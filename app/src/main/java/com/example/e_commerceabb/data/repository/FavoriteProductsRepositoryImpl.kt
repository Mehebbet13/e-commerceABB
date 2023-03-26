package com.example.e_commerceabb.data.repository

import com.example.e_commerceabb.data.api.BaseRepository
import com.example.e_commerceabb.data.api.FavoritesApi
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.domain.repository.FavoriteProductsRepository
import com.example.e_commerceabb.models.FavoriteProducts
import javax.inject.Inject

class FavoriteProductsRepositoryImpl @Inject constructor(private val favoritesApi: FavoritesApi) :
    FavoriteProductsRepository, BaseRepository() {
    override suspend fun getFavoriteProducts(): Resource<FavoriteProducts> {
        return safeApiCall { favoritesApi.getFavoriteProducts() }
    }

    override suspend fun addFavoriteProduct(productId: String): Resource<FavoriteProducts> {
        return safeApiCall { favoritesApi.addFavoriteProduct(productId) }
    }

    override suspend fun deleteFavoriteProduct(productId: String): Resource<FavoriteProducts> {
        return safeApiCall { favoritesApi.deleteFavoriteProduct(productId) }
    }
}
