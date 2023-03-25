package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class ProductResponse(
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
    @SerializedName("currentPrice")
    val currentPrice: Double? = null,
    @SerializedName("previousPrice")
    val previousPrice: Double? = null,
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
    val date: String? = null
)