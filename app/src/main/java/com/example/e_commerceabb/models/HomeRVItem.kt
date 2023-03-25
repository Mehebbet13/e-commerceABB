package com.example.e_commerceabb.models


interface HomeRVItem
data class TitleModel(
    val title: String? = null,
    val seeAll: String? = null
) : HomeRVItem

data class HomeCategoryListModel(
    val list: ArrayList<HomeCategoryModel>?
) : HomeRVItem

data class HomeProductsListModel(
    val title: TitleModel,
    val list: List<HomeProductsModel>
) : HomeRVItem


//
//    data class ForYouBonusesModel(
//        val list: ArrayList<HorizontalRecyclerViewModel>?
//    ) : HomeRVItem
//
//    data class ForYouOffersModel(
//        val headerTitle: String?,
//        val list: ArrayList<HorizontalRecyclerViewModel>
//    ) : HomeRVItem
//
//    data class ForYouChatModel(
//        val backgroundImageUrl: String?
//    ) : HomeRVItem
//
//    data class ForYouStoryModel(
//        val list: ArrayList<HorizontalRecyclerViewModel>?
//    ) : HomeRVItem

