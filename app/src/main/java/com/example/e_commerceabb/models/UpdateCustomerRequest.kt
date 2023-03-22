package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class UpdateCustomerRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("login")
    val login: String
)