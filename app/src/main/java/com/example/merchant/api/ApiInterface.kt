package com.example.merchant.api

import com.example.merchant.models.DefaultResponse
import com.example.merchant.models.LoginResponse
import com.example.merchant.models.MyDataItem
import com.example.merchant.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("api/v1/product/")
    fun getData(@Header("Authorization") token: String): Call<List<MyDataItem>>

    @FormUrlEncoded
    @POST("auth/register/")
    fun createUser(
        @Field("first_name") first_name:String,
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("auth/login/")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @POST("api/v1/product/")
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0NjI2MjI1LCJqdGkiOiJlZWFhYmRmMWI4YzU0OTJjYWMwYzM2YWJmNTRiOTVlZSIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.pUmHhL4LaM8EPw6XKfn6bP3X83BwIUXBLtL5f-yzfMg")
    fun createStock(@Body params: MyDataItem): Call<UserResponse>
}