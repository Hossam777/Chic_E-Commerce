package com.app.chic_ecommerce.shopproductsfragment.data

import com.app.chic_ecommerce.shopproductsfragment.data.entities.GetCategoriesResponse
import io.reactivex.Single

class GetCategoriesRepository (private val getCategoriesAPI: GetCategoriesAPI) {
    fun fetchGetCategories(): Single<GetCategoriesResponse> {
        return getCategoriesAPI.getCategories()
    }
}