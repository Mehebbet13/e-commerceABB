package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class UpdatePasswordRequest(
    @SerializedName("newPassword")
    val newPassword: String,
    @SerializedName("password")
    val password: String
)