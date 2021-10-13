package com.example.merchant.models

data class LoginResponse(
    val error: Boolean,
    val message:String,
    val user: User,
    val Token: String
)
