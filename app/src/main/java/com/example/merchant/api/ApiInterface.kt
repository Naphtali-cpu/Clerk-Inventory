package com.example.merchant.api

import com.example.merchant.models.DefaultResponse
import com.example.merchant.models.LoginResponse
import com.example.merchant.models.MyDataItem
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

    @POST("api/v1/product/")
    fun addProduct(@Header("Authorization") token: String): Call<List<MyDataItem>>

    @FormUrlEncoded
    @POST("auth/login/")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ): Call<LoginResponse>
}