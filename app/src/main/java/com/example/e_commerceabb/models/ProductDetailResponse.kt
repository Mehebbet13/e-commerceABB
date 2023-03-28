package com.example.e_commerceabb.models

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("enabled")
    val enabled: Boolean? = null,
    @SerializedName("imageUrls")
    val imageUrls: List<String>? = null,
    @SerializedName("quantity")
    val quantity: Int? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("currentPrice")
    val currentPrice: Int? = null,
    @SerializedName("previousPrice")
    val previousPrice: Int? = null,
    @SerializedName("categories")
    val categories: String? = null,
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("productUrl")
    val productUrl: String? = null,
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("myCustomParam")
    val myCustomParam: String? = null,
    @SerializedName("itemNo")
    val itemNo: String? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("__v")
    val v: Int? = null,
    @SerializedName("oneMoreCustomParam")
    val oneMoreCustomParam: OneMoreCustomParam
)