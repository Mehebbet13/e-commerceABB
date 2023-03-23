package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class UpdatePasswordResponse(
    @SerializedName("customer")
    val customer: Customer,
    @SerializedName("message")
    val message: String
)