package com.example.e_commerceabb.models

data class HomeProductsModel(
    val image: String,
    val discountLAbel: String,
    val title: String,
    val subtitle: String,
    val discountAmount: String,
    val amount: String,
    val onFavIconClick: () -> Unit,
    val onItemCLick: () -> Unit
) : HomeRVItem
