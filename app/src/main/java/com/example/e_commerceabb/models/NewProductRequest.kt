package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class NewProductRequest(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("currentPrice")
    val currentPrice: Double? = null,
    @SerializedName("previousPrice")
    val previousPrice: Int? = null,
    @SerializedName("categories")
    val categories: String? = null,
    @SerializedName("imageUrls")
    val imageUrls: List<String>? = null,
    @SerializedName("quantity")
    val quantity: Int? = null,
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("productUrl")
    val productUrl: String? = null,
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("myCustomParam")
    val myCustomParam: String? = null
)