package com.app.chic_ecommerce.loginactivity.data.entities

data class LoginResponse (
    val code: Int,
    val message: String,
    val token: String,
    val user: LoggedInUser,
)