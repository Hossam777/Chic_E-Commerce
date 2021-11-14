package com.app.chic_ecommerce.shopproductscategoryfragment.data.entities

import com.app.chic_ecommerce.common.data.entities.Product

data class GetProductsByFilterResponse(
    val code: Int,
    val products: MutableList<Product>
)
