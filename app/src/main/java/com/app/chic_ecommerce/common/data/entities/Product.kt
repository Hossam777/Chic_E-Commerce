package com.app.chic_ecommerce.common.data.entities

data class Product(
    val id: String,
    val name: String,
    val image: String,
    val price: Double,
    val type: String,
    val voucher: Int,
)
