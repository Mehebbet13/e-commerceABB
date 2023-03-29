package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class AddCommentRequest(
    @SerializedName("content")
    val content: String,
    @SerializedName("product")
    val productId: String
)