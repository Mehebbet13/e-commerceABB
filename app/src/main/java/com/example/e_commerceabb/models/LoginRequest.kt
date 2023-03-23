package com.example.e_commerceabb.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("loginOrEmail")
    val loginOrEmail: String,
    @SerializedName("password")
    val password: String
)
