package com.example.e_commerceabb.models

import com.google.gson.annotations.SerializedName

data class FilteredResponse(
    @SerializedName("products")
    val products:List<ProductResponse>,
    @SerializedName("productsQuantity")
    val productsQuantity: Int
)
