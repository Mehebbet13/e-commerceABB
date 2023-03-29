package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("enabled")
    val enabled: Boolean? = null,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("quantity")
    val quantity: Int? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
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
    @SerializedName("someOtherFeature")
    val someOtherFeature: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("ram")
    val ram: String,
    @SerializedName("weight")
    val weight: String,
    @SerializedName("itemNo")
    val itemNo: String,
    @SerializedName("__v")
    val v: Int,
    @SerializedName("date")
    val date: String
)