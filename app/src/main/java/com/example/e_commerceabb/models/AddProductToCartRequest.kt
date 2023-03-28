package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class AddProductToCartRequest(
    @SerializedName("products")
    val products: List<CartProductsRequest>
)