package com.app.chic_ecommerce.loginactivity.data.entities

data class LoggedInUser(
    val id: Int,
    val username: String,
    val email: String,
    val phone: String,
    val created_at: String,
    val updated_at: String
)
