package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class ProductXXXXX(
    @SerializedName("categories")
    val categories: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("currentPrice")
    val currentPrice: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("fabric")
    val fabric: String,
    @SerializedName("_id")
    val _id: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("itemNo")
    val itemNo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("previousPrice")
    val previousPrice: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("size")
    val size: String,
    @SerializedName("__v")
    val v: Int
)