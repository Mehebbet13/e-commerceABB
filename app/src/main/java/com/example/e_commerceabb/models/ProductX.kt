package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("brand")
    val brand: String,
    @SerializedName("categories")
    val categories: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("currentPrice")
    val currentPrice: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("_id")
    val id: String,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("itemNo")
    val itemNo: String,
    @SerializedName("myCustomParam")
    val myCustomParam: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("oneMoreCustomParam")
    val oneMoreCustomParam: OneMoreCustomParam,
    @SerializedName("previousPrice")
    val previousPrice: Int,
    @SerializedName("productUrl")
    val productUrl: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("__v")
    val v: Int
)