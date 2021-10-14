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

    @GET("api/v1/product/")
    fun getData(): Call<List<MyDataItem>>

    @FormUrlEncoded
    @POST("auth/register/")
    fun createUser(
        @Field("first_name") first_name:String,
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("api/v1/product/")
    fun addProduct(
        @Field("name") name:String,
        @Field("slug") slug:String,
    ): Call<DefaultResponse>


    @FormUrlEncoded
    @POST("auth/login/")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ): Call<LoginResponse>
}