package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class PlaceOrderRequest(
    @SerializedName("customerId")
    val customerId: String,
    @SerializedName("deliveryAddress")
    val deliveryAddress: DeliveryAddress? = null,
    @SerializedName("email")
    val email: String = "saribeg@gmail.com",
    @SerializedName("letterHtml")
    val letterHtml: String = "<h1>Your order is placed. OrderNo is 023689452.</h1><p>{Other details about order in your HTML}</p>",
    @SerializedName("letterSubject")
    val letterSubject: String = "Thank you for order! You are welcome!",
    @SerializedName("mobile")
    val mobile: String = "+380630000000",
    @SerializedName("paymentInfo")
    val paymentInfo: String? = null,
    @SerializedName("shipping")
    val shipping: String? = null,
    @SerializedName("products")
    val products: CartProductsResponse? = null,
    @SerializedName("status")
    val status: String? = null
)