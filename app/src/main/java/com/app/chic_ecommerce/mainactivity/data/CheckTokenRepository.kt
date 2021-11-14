package com.app.chic_ecommerce.mainactivity.data

import com.app.chic_ecommerce.mainactivity.data.entities.CheckTokenResponse
import io.reactivex.Single

class CheckTokenRepository(private val checkTokenAPI: CheckTokenAPI) {
    fun fetchCheckToken(token: String): Single<CheckTokenResponse>{
        return checkTokenAPI.checkToken("Bearer " + token)
    }
}