package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.CatalogResponse
import com.example.e_commerceabb.models.ProductDetailResponse
import com.example.e_commerceabb.models.ProductResponse

interface ProductRepository {
    suspend fun getProducts(): Resource<ArrayList<ProductResponse>>
    suspend fun getProductDetails(itemNo: String): Resource<ProductDetailResponse>
}