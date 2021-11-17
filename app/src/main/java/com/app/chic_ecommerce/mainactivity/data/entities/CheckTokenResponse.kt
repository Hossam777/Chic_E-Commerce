package com.app.chic_ecommerce.mainactivity.data.entities

data class CheckTokenResponse(
    val code: Int,
    val tokenIsValid: Boolean,
    val user: LoggedInUser,
)