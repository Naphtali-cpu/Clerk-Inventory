package com.example.merchant.api

import com.example.merchant.data.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("api/v1/product/")
    fun getData(@Header("Authorization") token: String): Call<List<MyDataItem>>

    @GET("api/v1/buyer/")
    fun getBuyers(@Header("Authorization") token: String): Call<List<Buyers>>

    @GET("api/v1/delivery/")
    fun getDelivery(@Header("Authorization") token: String): Call<List<Deliveries>>

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
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0NzcxNDUzLCJqdGkiOiI4NWYzZWE2NTA0ZDk0YjI2YjFiZjgyOTVhOWE1ZTkyYyIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.byieEawL1gMgas0A5gPZywbPL8aHr3tpPjO0VteMz7Y")
    fun createStock(@Body params: MyDataItem): Call<UserResponse>

    @DELETE("api/v1/product/{id}/")
    fun deleteStock(@Path("sortno") id: Int) : Call<Unit>
}