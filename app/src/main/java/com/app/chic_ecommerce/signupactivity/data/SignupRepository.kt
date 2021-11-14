package com.app.chic_ecommerce.signupactivity.data

import com.app.chic_ecommerce.signupactivity.data.entities.SignupResponse
import io.reactivex.Single

class SignupRepository(private val signupAPI: SignupAPI) {
    fun fetchSignup(username: String, mail: String, password: String, phone: String): Single<SignupResponse> {
        return signupAPI.signup(username, mail, password, phone)
    }
}