package com.app.chic_ecommerce.common.data.entities

data class Product(
    val id: String,
    val name: String,
    val image1: String,
    val image2: String,
    val image3: String,
    val price: Double,
    val sale: Int,
    val sizes: String,
    val colors: String,
    val description: String,
    val category: String,
    val subcategory: String,
) {
    override fun toString(): String {
        return "$id,$name,$image1,$image2,$image3,$price,$sale,$sizes,$colors,$description,$category,$subcategory"
    }
    constructor(p: List<String>) : this(p[0], p[1], p[2], p[3], p[4], p[5].toDouble(), p[6].toInt(), p[4], p[5], p[6], p[4], p[5])
}
