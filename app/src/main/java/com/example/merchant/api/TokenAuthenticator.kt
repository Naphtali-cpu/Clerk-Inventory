package com.example.merchant.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.merchant.data.models.LoginResponse
import com.example.merchant.sessions.SessionManager
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
    private val sessionManager: SessionManager,
    private val retrofit: RetroInstance
) : Authenticator {

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val appContext = context.applicationContext
    private val BASE_REFRESH = "https://one-stocks.herokuapp.com/"
    override fun authenticate(route: Route?, response: Response): Request? {
        val tokenResponse = getUpdatedToken()
        if (response.code == 403 || response.code == 401) {
            sessionManager.saveAuthToken(tokenResponse.access)
        } else {
            Toast.makeText(appContext, response.message, Toast.LENGTH_LONG).show()
        }


        return null
    }


    private fun getUpdatedToken(): LoginResponse {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_REFRESH)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val refreshToken = sessionManager.fetchAuthToken()
        val service = retrofit.create(ApiInterface::class.java).refreshToken(refreshToken!!)
        Log.i("AUTH", service.toString())
        return service

    }



}