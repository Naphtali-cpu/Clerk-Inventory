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

    @POST("auth/token/refresh/")
    @FormUrlEncoded
    fun refreshToken(@Field("refresh") refreshToken: String): LoginResponse

    @POST("api/v1/product/")
    @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0ODUxMzE3LCJqdGkiOiJlOWNlY2MwYjcyZjA0NjVkYTYyYzkwMDNlNzdlYmQ4ZCIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.TB9VoKba1FZ_7QK10BVVVsm9dJcWYw6FfZjWhfWgYAs")
    fun createStock(@Body params: MyDataItem): Call<UserResponse>

    @FormUrlEncoded
    @POST("api/v1/delivery/")
    fun createDelivery(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("order") order: String,
        @Field("courier_name") courier_name: String,
        @Field("slug") slug: String
    ): Call<Deliveries>

    @DELETE("api/v1/product/{id}/")
    fun deleteStock(@Path("id") id: Int) : Call<Unit>

    @FormUrlEncoded
    @PUT("api/v1/product/{id}/")
    fun updateStock(
        @Path("id") id: Int,
        @Field("name") name: String,
        @Field("slug") slug: String,
        @Field("sortno") sortno: String,
    ): Call<MyDataItem>

    @DELETE("api/v1/deliveriy/{id}/")
    fun deleteDelivery(@Path("id") id: Int) : Call<Unit>
}