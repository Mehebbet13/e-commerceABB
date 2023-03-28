package com.example.e_commerceabb.data.repository

import com.example.e_commerceabb.data.api.BaseRepository
import com.example.e_commerceabb.data.api.CatalogApi
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.domain.repository.CatalogRepository
import com.example.e_commerceabb.models.CatalogResponse
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(private val catalogApi: CatalogApi) :
    CatalogRepository, BaseRepository() {
    override suspend fun getCatalog(): Resource<ArrayList<CatalogResponse>> {
        return safeApiCall { catalogApi.getCatalog() }
    }
}