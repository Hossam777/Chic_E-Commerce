package com.app.chic_ecommerce.common.data.entities

data class CartProduct(
    val id: String,
    val name: String,
    val image: String,
    val size: String,
    val color: String,
    val price: Double,
    val quantity: Int,
) {
    override fun toString(): String {
        return "$id,$name,$image,$size,$color,$price,$quantity"
    }
    constructor(p: List<String>) : this(p[0], p[1], p[2], p[3], p[4], p[5].toDouble(), p[6].toInt())
}
