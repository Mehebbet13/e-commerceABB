package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class ProductXXXX(
    @SerializedName("cartQuantity")
    val cartQuantity: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("product")
    val product: ProductResponse
)