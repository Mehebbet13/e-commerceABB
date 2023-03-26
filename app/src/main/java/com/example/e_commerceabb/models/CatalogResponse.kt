package com.example.e_commerceabb.models


import com.google.gson.annotations.SerializedName

data class CatalogResponse(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("parentId")
    val parentId: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("__v")
    val v: Int
)