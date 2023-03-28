package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.CatalogResponse
import retrofit2.Response
import retrofit2.http.GET

interface CatalogApi {
    @GET("catalog")
    suspend fun getCatalog(): Response<ArrayList<CatalogResponse>>
}