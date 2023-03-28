package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class DeliveryAddress(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String = "Kiev",
    @SerializedName("country")
    val country: String = "Ukraine",
    @SerializedName("postal")
    val postal: String
)