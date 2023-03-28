package com.example.e_commerceabb.presentation.home.mapper

import com.example.e_commerceabb.R
import com.example.e_commerceabb.models.*
import com.example.e_commerceabb.utils.Constants.EMPTY
import javax.inject.Inject

class HomeUiMapper @Inject constructor() {
    var onSeeAllClick: (() -> Unit)? = null
    private fun mapProductsItemToUiModel(
        productList: ArrayList<ProductResponse>
    ): List<HomeProductsModel> {
        return productList.map { response ->
            HomeProductsModel(
                title = response.name ?: EMPTY,
                subtitle = response.description ?: EMPTY,
                discountLAbel = "-10%",
                discountAmount = "${response.currentPrice} ${"$"}",
                amount = "${response.previousPrice} ${"$"}",
                image = R.drawable.rectangle_14
                //  image = response.imageUrls?.get(1)?:"img/products/men/004.png"
            )
        }
    }

    fun mapProductsToUiModel(
        productList: ArrayList<ProductResponse>
    ): List<HomeProductsListModel> {
        return listOf(
            HomeProductsListModel(
                title = TitleModel(
                    title = "Products",
                    seeAll = "See all",
                    onClick = {
                        onSeeAllClick?.invoke()
                    }
                ),
                list = mapProductsItemToUiModel(productList)
            )
        )
    }

    private fun mapCategoryItemToUiModel(
        categoryList: ArrayList<CatalogResponse>
    ): List<HomeCategoryModel> {
        return categoryList.mapNotNull { response ->
            HomeCategoryModel(
                title = response.name,
                image = R.drawable.rectangle_14
                //   image = response.imgUrl
            )
        }
    }

    fun mapCategoryToUiModel(
        categoryList: ArrayList<CatalogResponse>
    ): List<HomeCategoryListModel> {
        return listOf(
            HomeCategoryListModel(
                title = TitleModel(
                    title = "Category",
                    onClick = {}
                ),
                list = mapCategoryItemToUiModel(categoryList)
            )
        )
    }


    companion object {
        const val STRING_FORMAT = "%i %s"

    }
}