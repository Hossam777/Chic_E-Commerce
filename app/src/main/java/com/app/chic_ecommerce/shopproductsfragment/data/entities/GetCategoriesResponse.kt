package com.app.chic_ecommerce.shopproductsfragment.data.entities

data class GetCategoriesResponse(
    val code: Int,
    val categories: MutableList<Category>
)
