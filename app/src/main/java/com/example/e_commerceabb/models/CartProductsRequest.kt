package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class CartProductsRequest(
    @SerializedName("cartQuantity")
    val cartQuantity: Int,
    @SerializedName("product")
    val product: String
)