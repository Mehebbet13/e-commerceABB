package com.example.e_commerceabb.data.repository

import com.example.e_commerceabb.data.api.BaseRepository
import com.example.e_commerceabb.data.api.ProductsApi
import com.example.e_commerceabb.data.api.Resource
import com.example.e_commerceabb.domain.repository.ProductRepository
import com.example.e_commerceabb.models.FilteredResponse
import com.example.e_commerceabb.models.ProductDetailResponse
import com.example.e_commerceabb.models.ProductResponse
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productsApi: ProductsApi) :
    ProductRepository, BaseRepository() {

    override suspend fun getProducts(): Resource<ArrayList<ProductResponse>> {
        return safeApiCall { productsApi.getProducts() }
    }

    override suspend fun getProductDetails(itemNo: String): Resource<ProductDetailResponse> {
        return safeApiCall { productsApi.getProductDetail(itemNo) }
    }

    override suspend fun getFilteredProduct(name: String): Resource<FilteredResponse> {
        return safeApiCall { productsApi.getFilterProduct(name) }
    }
}