package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class NewCustomerRequest(
    @SerializedName("avatarUrl")
    val avatarUrl: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("isAdmin")
    val isAdmin: Boolean,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("telephone")
    val telephone: String
)