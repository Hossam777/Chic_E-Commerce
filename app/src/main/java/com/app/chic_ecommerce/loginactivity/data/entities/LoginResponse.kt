package com.app.chic_ecommerce.loginactivity.data.entities

import com.app.chic_ecommerce.common.data.entities.User

data class LoginResponse (
    val code: Int,
    val message: String,
    val token: String,
    val user: LoggedInUser,
)