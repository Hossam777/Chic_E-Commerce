package com.app.chic_ecommerce.historyfragment.data.entities

data class OrderHeader(
    val id: Int,
    val userid: Int,
    val address: String,
    val totalprice: String,
    val promocode: String,
    val created_at: String,
    val updated_at: String,
)
