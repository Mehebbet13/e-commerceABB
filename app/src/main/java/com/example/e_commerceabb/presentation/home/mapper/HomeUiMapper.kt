package com.example.e_commerceabb.presentation.home.mapper

import com.example.e_commerceabb.R
import com.example.e_commerceabb.models.*
import com.example.e_commerceabb.utils.Constants.EMPTY
import javax.inject.Inject

class HomeUiMapper @Inject constructor() {
    fun mapProductsItemToUiModel(
        productList: ArrayList<ProductResponse>
    ): List<HomeProductsModel> {
        return productList.mapNotNull{response->
        HomeProductsModel(
            title = response.name ?: EMPTY,
            subtitle = response.name ?: EMPTY,
            discountLAbel = " -20%",
            discountAmount ="sdf",
            amount = "sdf",
            image = R.drawable.rectangle_14
        )
    }}

    fun mapProductsToUiModel(
        productList: ArrayList<ProductResponse>
    ): List<HomeProductsListModel> {
        return listOf(HomeProductsListModel(
            title = TitleModel(
                title = "Products"
            ),
            list = mapProductsItemToUiModel(productList)
        ),HomeProductsListModel(
            title = TitleModel(
                title = "Caarts"
            ),
            list = mapProductsItemToUiModel(productList)
        ))
    }


    companion object {
        const val STRING_FORMAT = "%i %s"

    }
}