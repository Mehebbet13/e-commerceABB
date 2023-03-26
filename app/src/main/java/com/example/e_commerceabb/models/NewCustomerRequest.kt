package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class NewCustomerRequest(
    @SerializedName("avatarUrl")
    val avatarUrl: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("isAdmin")
    val isAdmin: Boolean = false,
    @SerializedName("enabled")
    val enabled: Boolean = false,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("telephone")
    val telephone: String? = null,
    @SerializedName("customerNo")
    val customerNo: String? = null
)