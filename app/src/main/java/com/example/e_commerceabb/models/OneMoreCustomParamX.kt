package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class OneMoreCustomParamX(
    @SerializedName("description")
    val description: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("rate")
    val rate: Double
)