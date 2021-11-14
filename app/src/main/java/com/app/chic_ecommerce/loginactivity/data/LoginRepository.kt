package com.app.chic_ecommerce.loginactivity.data

import com.app.chic_ecommerce.loginactivity.data.entities.LoginResponse
import io.reactivex.Single

class LoginRepository(private val loginAPI: LoginAPI) {
    fun fetchLogin(email: String, password: String): Single<LoginResponse> {
        return loginAPI.login(email, password)
    }
}