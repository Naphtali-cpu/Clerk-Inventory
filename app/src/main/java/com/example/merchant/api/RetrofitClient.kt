package com.example.merchant.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val AUTH = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0ODUxMzE3LCJqdGkiOiJlOWNlY2MwYjcyZjA0NjVkYTYyYzkwMDNlNzdlYmQ4ZCIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.TB9VoKba1FZ_7QK10BVVVsm9dJcWYw6FfZjWhfWgYAs"

    private const val BASE_URL = "https://one-stocks.herokuapp.com/"
    val okhttpHttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", AUTH)
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.addInterceptor(
            okhttpHttpLoggingInterceptor
        ).build()

    val instance: ApiInterface by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ApiInterface::class.java)
    }

    fun provideOkHttpClient(
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {

        return OkHttpClient().newBuilder()
            .authenticator(tokenAuthenticator)
            .build()
    }


}