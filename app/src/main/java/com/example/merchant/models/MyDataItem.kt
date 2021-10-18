package com.example.merchant.models

data class MyDataItem(
    val id: Int,
    val name: String,
    val slug: String,
    val sortno: String,
    val created_date: String
    )

data class UserResponse(val code: Int?,
                        val meta: String?,
                        val data: User?
)