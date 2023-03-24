package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class UpdateCustomerRequest(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("fullName")
    val fullName: String? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String? = null,
    @SerializedName("avatarUrl")
    val avatarUrl: String? = null,
    @SerializedName("telephone")
    val telephone: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("login")
    val login: String? = null
)