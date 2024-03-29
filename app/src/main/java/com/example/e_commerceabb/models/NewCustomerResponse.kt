package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class NewCustomerResponse(
    @SerializedName("avatarUrl")
    val avatarUrl: String,
    @SerializedName("customerNo")
    val customerNo: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("isAdmin")
    val isAdmin: Boolean,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("telephone")
    val telephone: String,
    @SerializedName("__v")
    val v: Int
)