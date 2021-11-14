package com.app.chic_ecommerce.shopproductsfragment.data

import com.app.chic_ecommerce.common.data.API_URL
import com.app.chic_ecommerce.shopproductsfragment.data.entities.GetCategoriesResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GetCategoriesAPI {
    @GET("getcategories")
    fun getCategories(
    ): Single<GetCategoriesResponse>

    companion object{
        operator fun invoke(): GetCategoriesAPI {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GetCategoriesAPI::class.java)
        }
    }
}