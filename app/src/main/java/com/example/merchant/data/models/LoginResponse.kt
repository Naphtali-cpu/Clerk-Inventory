package com.example.merchant.data.models

data class LoginResponse(
    val error: Boolean,
    val detail: String,
    val message:String,
    val user: User,
    val Token: String,
    val statusCode: Int
)
