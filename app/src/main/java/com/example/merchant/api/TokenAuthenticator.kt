package com.example.merchant.api

import android.content.Context
import android.util.Log
import com.example.merchant.data.models.LoginResponse
import com.example.merchant.sessions.SharedPrefApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class TokenAuthenticator @Inject constructor(
    context: Context,
    private val tokenApi: ApiInterface,
    private val retrofit: RetroInstance
) : Authenticator {

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val appContext = context.applicationContext
    private val BASE_REFRESH = "https://one-stocks.herokuapp.com/"
    private val userPreferences = SharedPrefApi(appContext)
    override fun authenticate(route: Route?, response: Response): Request? {
        runBlocking {
            val tokenResponse = getUpdatedToken()
            when {
                response.code == 403 || response.code == 401 -> {
                    userPreferences.saveAccessTokens(
                        tokenResponse.access,
                        tokenResponse.refresh
                    )
                }
            }
        }
        return null
    }


    private suspend fun getUpdatedToken(): LoginResponse {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_REFRESH)
            .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
            .build()


        val refreshToken = userPreferences.refreshToken.first()
        val service = retrofit.create(ApiInterface::class.java).refreshToken(refreshToken!!)
        Log.i("AUTH", service.toString())
        return service

    }



}