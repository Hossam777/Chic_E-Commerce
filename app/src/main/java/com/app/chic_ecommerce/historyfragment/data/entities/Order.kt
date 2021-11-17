package com.app.chic_ecommerce.historyfragment.data.entities

data class Order(
    val header: OrderHeader,
    val products: List<OrderProduct>,
)
