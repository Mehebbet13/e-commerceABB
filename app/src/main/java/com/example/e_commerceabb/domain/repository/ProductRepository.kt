package com.example.e_commerceabb.domain.repository

import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.models.AddProductRequest
import com.example.e_commerceabb.models.CatalogResponse
import com.example.e_commerceabb.models.FilteredResponse
import com.example.e_commerceabb.models.ProductDetailResponse
import com.example.e_commerceabb.models.ProductResponse
import com.example.e_commerceabb.models.*

interface ProductRepository {
    suspend fun getProducts(): Resource<ArrayList<ProductResponse>>
    suspend fun addProduct(request: AddProductRequest): Resource<ProductResponse>
    suspend fun getProductDetails(itemNo: String): Resource<ProductDetailResponse>
    suspend fun getFilteredProduct(name: String): Resource<FilteredResponse>
    suspend fun search(query: SearchRequest): Resource<ArrayList<ProductResponse>>
    suspend fun getComment(productId: String): Resource<ArrayList<CommentsResponse>>
}