package com.example.merchant.data.models

data class LoginResponse(
    val error: Boolean,
    val detail: String,
    val message:String,
    val user: User,
    val refresh: String,
    val access: String,
    val statusCode: Int
)
