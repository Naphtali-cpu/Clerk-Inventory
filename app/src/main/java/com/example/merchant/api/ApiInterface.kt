package com.example.merchant.api

import com.example.merchant.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("api/v1/product/")
    fun getData(@Header("Authorization") token: String): Call<List<MyDataItem>>

    @GET("api/v1/buyer/")
    fun getBuyers(@Header("Authorization") token: String): Call<List<Buyers>>

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
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0Njk3ODcyLCJqdGkiOiI2ZGY1MDdiNjUxNDk0MDNjYTBmMTkyNzliYWE3YzM0YSIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.CiVdQD3JG4R_HOUkGPXEpfJPu18jwX5Bc5qFYqb04IU")
    fun createStock(@Body params: MyDataItem): Call<UserResponse>

    @DELETE("api/v1/product/{id}/")
    fun deleteStock(@Path("id") id: Int) : Call<Unit>
}