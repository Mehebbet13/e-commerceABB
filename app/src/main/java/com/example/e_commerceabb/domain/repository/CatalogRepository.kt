package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.CatalogResponse

interface CatalogRepository {
    suspend fun getCatalog(): Resource<ArrayList<CatalogResponse>>
}