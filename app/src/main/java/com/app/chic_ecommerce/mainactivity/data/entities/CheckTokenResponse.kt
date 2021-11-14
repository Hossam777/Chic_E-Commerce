package com.app.chic_ecommerce.mainactivity.data.entities

import com.app.chic_ecommerce.common.data.entities.User

data class CheckTokenResponse(
    val code: Int,
    val tokenIsValid: Boolean,
    val user: LoggedInUser,
)