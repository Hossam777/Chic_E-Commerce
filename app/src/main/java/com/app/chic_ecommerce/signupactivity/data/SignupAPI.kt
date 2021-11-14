package com.app.chic_ecommerce.signupactivity.data

import com.app.chic_ecommerce.common.data.API_URL
import com.app.chic_ecommerce.signupactivity.data.entities.SignupResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignupAPI {
    @FormUrlEncoded
    @POST("signup")
    fun signup(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
    ): Single<SignupResponse>

    companion object{
        operator fun invoke(): SignupAPI {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SignupAPI::class.java)
        }
    }
}