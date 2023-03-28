package com.example.e_commerceabb.data.api

import com.example.e_commerceabb.models.*
import retrofit2.Response
import retrofit2.http.*

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): Response<ArrayList<ProductResponse>>

    @POST("products")
    suspend fun addProduct(
        @Body request: AddProductRequest
    ): Response<ProductResponse>

    @GET("products/{itemNo}")
    suspend fun getProductDetail(
        @Path("itemNo") itemNo: String
    ): Response<ProductDetailResponse>

    @GET("products/filter")
    suspend fun getFilterProduct(
        @Query("userId") userId: String
    ): Response<FilteredResponse>
}
