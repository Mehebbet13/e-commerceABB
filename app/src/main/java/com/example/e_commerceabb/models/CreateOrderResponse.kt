package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class CreateOrderResponse(
    @SerializedName("mailResult")
    val mailResult: MailResult,
    @SerializedName("order")
    val order: OrderX
)