package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class CartProductsResponse(
    @SerializedName("customerId")
    val customerId: CustomerId,
    @SerializedName("date")
    val date: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("products")
    val products: List<ProductXX>,
    @SerializedName("__v")
    val v: Int
)