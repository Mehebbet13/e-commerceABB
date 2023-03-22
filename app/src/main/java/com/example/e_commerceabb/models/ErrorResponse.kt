package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("failure_message")
    val failureMessage: String,
    @SerializedName("status")
    val status: String
)