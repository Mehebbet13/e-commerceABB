package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class CustomerOrdersResponseItem(
    @SerializedName("canceled")
    val canceled: Boolean,
    @SerializedName("customerId")
    val customerId: CustomerId,
    @SerializedName("date")
    val date: String,
    @SerializedName("deliveryAddress")
    val deliveryAddress: DeliveryAddress,
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("letterHtml")
    val letterHtml: String,
    @SerializedName("letterSubject")
    val letterSubject: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("orderNo")
    val orderNo: String,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalSum")
    val totalSum: Int,
    @SerializedName("__v")
    val v: Int
)