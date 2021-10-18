package com.demo.retrofithttpmethods

import com.example.merchant.models.MyDataItem
import com.example.merchant.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetroServiceInterface {

    @POST("api/v1/product/")
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0NjI2MjI1LCJqdGkiOiJlZWFhYmRmMWI4YzU0OTJjYWMwYzM2YWJmNTRiOTVlZSIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.pUmHhL4LaM8EPw6XKfn6bP3X83BwIUXBLtL5f-yzfMg")
    fun createStock(@Body params: MyDataItem): Call<UserResponse>
}