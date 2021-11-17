package com.app.chic_ecommerce.historyfragment.data

import com.app.chic_ecommerce.common.data.API_URL
import com.app.chic_ecommerce.historyfragment.data.entities.GetOrdersByUserResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.POST

interface GetOrdersByUserAPI {

    @POST("getordersbyuser")
    fun getOrdersByUser(
        @Header("Authorization") authorization: String
    ): Single<GetOrdersByUserResponse>

    companion object{
        operator fun invoke(): GetOrdersByUserAPI {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GetOrdersByUserAPI::class.java)
        }
    }
}