package com.app.chic_ecommerce.shopproductscategoryfragment.data

import com.app.chic_ecommerce.shopproductscategoryfragment.data.entities.GetSubCategoriesResponse
import io.reactivex.Single

class GetSubCategoriesRepository (private val getCategoriesAPI: GetSubCategoriesAPI) {
    fun fetchGetCategories(): Single<GetSubCategoriesResponse> {
        return getCategoriesAPI.getSubCategories()
    }
}