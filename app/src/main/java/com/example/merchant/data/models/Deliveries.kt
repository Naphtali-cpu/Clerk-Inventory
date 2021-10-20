package com.example.merchant.data.models

data class Deliveries(
    val id: Int,
    val name: String,
    val order: String,
    val courier_name: String,
    val created_date: String,
    val slug: String
)
