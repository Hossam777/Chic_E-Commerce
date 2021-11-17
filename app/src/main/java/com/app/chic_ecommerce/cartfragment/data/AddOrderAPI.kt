package com.app.chic_ecommerce.cartfragment.data

import com.app.chic_ecommerce.cartfragment.data.entities.AddOrderResponse
import com.app.chic_ecommerce.common.data.API_URL
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AddOrderAPI {
    @POST("addorder")
    fun addOrderAPI(
        @Header("Authorization") authorization: String,
        @Body body: JsonObject,
    ): Single<AddOrderResponse>

    companion object{
        operator fun invoke(): AddOrderAPI {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(AddOrderAPI::class.java)
        }
    }
}