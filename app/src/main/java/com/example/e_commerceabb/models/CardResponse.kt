package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("customerId")
    val customerId: CustomerId,
    @SerializedName("date")
    val date: String,
    @SerializedName("__v")
    val v: Int
)