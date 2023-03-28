package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.CardResponse
import com.example.e_commerceabb.models.FilteredResponse
import com.example.e_commerceabb.models.ProductDetailResponse
import com.example.e_commerceabb.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): Response<ArrayList<ProductResponse>>

    @GET("products/{itemNo}")
    suspend fun getProductDetail(
        @Path("itemNo") itemNo: String
    ): Response<ProductDetailResponse>

    @GET("products/filter")
    suspend fun getFilterProduct(
        @Query("querystring") queryString: String
    ): Response<FilteredResponse>

}