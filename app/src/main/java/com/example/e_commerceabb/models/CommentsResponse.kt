package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("customer")
    val customer: Customer,
    @SerializedName("product")
    val product: Product,
    @SerializedName("content")
    val content: String,
    @SerializedName("__v")
    val v: Int
)