package com.app.chic_ecommerce.historyfragment.data.entities

data class OrderProduct(
    val id: Int,
    val orderid: Int,
    val productid: Int,
    val productname: String,
    val productimg: String,
    val price: String,
    val size: String,
    val color: String,
    val quantity: Int,
)
