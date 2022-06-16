package com.example.merchant.api

import com.example.merchant.data.models.LoginResponse
import com.example.merchant.sessions.SharedPrefApi
import retrofit2.Call

class AccessTokenWrapper constructor(private val sharedPrefApi: SharedPrefApi) {
    private var accessToken: LoginResponse? = null

    fun getAccessToken(): LoginResponse? {
        if (accessToken == null) {
            accessToken = sharedPrefApi.getObject(SharedPrefApi.ACCESS_TOKEN, LoginResponse::class.java)
        }
        return accessToken
    }

    fun saveAccessToken(accessToken: LoginResponse) {
        this.accessToken = accessToken
        sharedPrefApi.putObject(SharedPrefApi.ACCESS_TOKEN, accessToken)
    }
}