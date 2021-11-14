package com.app.chic_ecommerce.shopproductscategoryfragment.data

import com.app.chic_ecommerce.common.data.API_URL
import com.app.chic_ecommerce.shopproductscategoryfragment.data.entities.GetProductsByFilterResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GetProductsByFilterAPI {
    @GET("getproductbyfilters")
    fun getProductByFilters(
    ): Single<GetProductsByFilterResponse>

    companion object{
        operator fun invoke(): GetProductsByFilterAPI {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GetProductsByFilterAPI::class.java)
        }
    }
}