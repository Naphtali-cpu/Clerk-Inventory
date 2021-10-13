package com.example.merchant.api

import com.example.merchant.models.DefaultResponse
import com.example.merchant.models.LoginResponse
import com.example.merchant.models.MyDataItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("news")
    fun getData(): Call<List<MyDataItem>>

    @FormUrlEncoded
    @POST("auth/api/v1/register/")
    fun createUser(
        @Field("email") email:String,
        @Field("username") username:String,
        @Field("password") password:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("auth/api/v1/login/")
    fun userLogin(
        @Field("username") username:String,
        @Field("password") password: String
    ): Call<LoginResponse>
}