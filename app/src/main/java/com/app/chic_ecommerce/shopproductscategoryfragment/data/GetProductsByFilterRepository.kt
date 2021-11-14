package com.app.chic_ecommerce.shopproductscategoryfragment.data

import com.app.chic_ecommerce.shopproductscategoryfragment.data.entities.GetProductsByFilterResponse
import io.reactivex.Single

class GetProductsByFilterRepository (private val getProductsByFilterAPI: GetProductsByFilterAPI) {
    fun fetchGetProductsByFilter(): Single<GetProductsByFilterResponse> {
        return getProductsByFilterAPI.getProductByFilters()
    }
}