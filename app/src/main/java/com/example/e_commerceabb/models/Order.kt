package com.example.e_commerceabb.models

import com.example.e_commerceabb.utils.Constants.EMPTY

data class Order(
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: String,
    val buttonName: String = EMPTY,
    val status: String = EMPTY,
    val productId: String = EMPTY
)
