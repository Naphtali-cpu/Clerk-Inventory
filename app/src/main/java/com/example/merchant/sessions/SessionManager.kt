package com.example.merchant.sessions

import android.content.Context
import android.content.SharedPreferences
import com.example.merchant.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to save refresh token
     */
    fun saveRefreshToken(reftoken: String) {
        val refeditor = prefs.edit()
        refeditor.putString(REFRESH_TOKEN, reftoken)
        refeditor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    /**
     * Function to fetch refresh token
     */
    fun fetchRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN, null)
    }
}