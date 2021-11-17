package com.app.chic_ecommerce.cartfragment.data

import com.app.chic_ecommerce.cartfragment.data.entities.AddOrderResponse
import com.app.chic_ecommerce.common.data.entities.CartProduct
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Single

class AddOrderRepository (private val addOrderAPI: AddOrderAPI) {
    fun fetchAddOrder(token: String, promoCode: String, address: String, totalPrice: Double, cartProducts: MutableList<CartProduct>): Single<AddOrderResponse> {
        val productsArray = JsonArray()
        cartProducts.forEach {
            val productElement = JsonObject()
            productElement.addProperty("productid", it.id)
            productElement.addProperty("productname", it.name)
            productElement.addProperty("productimg", it.image)
            productElement.addProperty("price", it.price)
            productElement.addProperty("size", it.size)
            productElement.addProperty("color", it.color)
            productElement.addProperty("quantity", it.quantity)
            productsArray.add(productElement)
        }
        val body = JsonObject()
        body.addProperty("address", address)
        body.addProperty("promocode", promoCode)
        body.addProperty("totalprice", totalPrice)
        body.add("products", productsArray)
        return addOrderAPI.addOrderAPI("Bearer " + token, body)
    }
}