package com.app.chic_ecommerce.historyfragment.data.entities

data class GetOrdersByUserResponse(
    val code: Int,
    val orders: List<Order>
)
