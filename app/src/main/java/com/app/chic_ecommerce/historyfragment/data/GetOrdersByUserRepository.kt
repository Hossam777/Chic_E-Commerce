package com.app.chic_ecommerce.historyfragment.data

import com.app.chic_ecommerce.historyfragment.data.entities.GetOrdersByUserResponse
import io.reactivex.Single

class GetOrdersByUserRepository (private val getOrdersByUserAPI: GetOrdersByUserAPI) {
    fun fetchGetOrdersByUser (token: String): Single<GetOrdersByUserResponse> {
        return getOrdersByUserAPI.getOrdersByUser("Bearer " + token)
    }
}