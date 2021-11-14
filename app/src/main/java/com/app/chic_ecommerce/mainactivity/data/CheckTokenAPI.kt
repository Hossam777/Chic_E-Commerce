package com.app.chic_ecommerce.mainactivity.data

import com.app.chic_ecommerce.common.data.API_URL
import com.app.chic_ecommerce.mainactivity.data.entities.CheckTokenResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.POST

interface CheckTokenAPI {

    @POST("checktoken")
    fun checkToken(
        @Header("Authorization") authorization: String
    ): Single<CheckTokenResponse>

    companion object{
        operator fun invoke(): CheckTokenAPI {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CheckTokenAPI::class.java)
        }
    }
}