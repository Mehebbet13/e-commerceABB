package com.example.e_commerceabb.models


interface HomeRVItem
data class TitleModel(
    val title: String? = null,
    val seeAll: String? = null
) : HomeRVItem

data class HomeCategoryListModel(
    val title: TitleModel,
    val list: List<HomeCategoryModel>
) : HomeRVItem

data class HomeProductsListModel(
    val title: TitleModel,
    val list: List<HomeProductsModel>
) : HomeRVItem
