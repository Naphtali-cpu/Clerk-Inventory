package com.example.merchant.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val AUTH = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0MzM1NjY0LCJqdGkiOiJlZTVmZDlkMjYyN2Q0ZjQyODFkN2NiOWYwNGFmMWZlYiIsInVzZXJfaWQiOjEsInJvbGUiOiJTVVBQTElFUiIsInVzZXJuYW1lIjoibmFwaHRhbGk5MTlAZ21haWwuY29tIn0.YDZZqOXQRXG2P8g3fkxfXKM16oqzdvT-ncScsCQTlR0"

    private const val BASE_URL = "https://stockinvent.herokuapp.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", AUTH)
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: ApiInterface by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ApiInterface::class.java)
    }
}