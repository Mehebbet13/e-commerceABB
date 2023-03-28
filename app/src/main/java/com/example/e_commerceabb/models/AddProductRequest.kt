package com.example.e_commerceabb.models

import com.google.gson.annotations.SerializedName

data class AddProductRequest(
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("categories")
    val categories: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("currentPrice")
    val currentPrice: Double? = null,
    @SerializedName("imageUrls")
    val imageUrls: List<String>? = null,
    @SerializedName("myCustomParam")
    val myCustomParam: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("previousPrice")
    val previousPrice: Int? = null,
    @SerializedName("productUrl")
    val productUrl: String? = null,
    @SerializedName("userId")
    val userId: String? = null,
    @SerializedName("quantity")
    val quantity: Int? = 20
)
