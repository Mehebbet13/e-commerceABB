package com.example.e_commerceabb.models
import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("currentPrice")
    val currentPrice: Int,
    @SerializedName("previousPrice")
    val previousPrice: Int,
    @SerializedName("categories")
    val categories: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("productUrl")
    val productUrl: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("myCustomParam")
    val myCustomParam: String,
    @SerializedName("itemNo")
    val itemNo: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("__v")
    val v: Int,
    @SerializedName("oneMoreCustomParam")
    val oneMoreCustomParam: OneMoreCustomParam
)