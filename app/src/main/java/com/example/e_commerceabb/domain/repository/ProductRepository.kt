package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.*

interface ProductRepository {
    suspend fun getProducts(): Resource<ArrayList<ProductResponse>>
    suspend fun getProductDetails(itemNo: String): Resource<ProductDetailResponse>
    suspend fun getFilteredProduct(name: String): Resource<FilteredResponse>
    suspend fun search(query: SearchRequest): Resource<ArrayList<ProductResponse>>
}