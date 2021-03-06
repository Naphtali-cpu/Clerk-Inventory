package com.example.merchant.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.merchant.R
import com.example.merchant.api.RetrofitClient
import com.example.merchant.data.models.LoginResponse
import com.example.merchant.navigationbaractivities.Dashboard
import com.example.merchant.sessions.LoginPref
import com.example.merchant.sessions.SessionManager
import com.example.merchant.sessions.SharedPrefApi
//import com.example.merchant.data.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    lateinit var session: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        session = LoginPref(this)
        val button = findViewById(R.id.btnLogin) as Button
        button.setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isEmpty()) {
                editTextEmail.error = "Email required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }


            if (password.isEmpty()) {
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            sessionManager = SessionManager(this)

            RetrofitClient.instance.userLogin(email, password)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val loginResponse = response.body()
//                        Saving our token and refresh token to the preferences
                        sessionManager.saveAuthToken(loginResponse!!.access)
                        sessionManager.saveRefreshToken(loginResponse.refresh)

//                        Saving our login session in our LoginPreferences
                        session.createLoginSession(email, password)
                        val intent = Intent(applicationContext, Dashboard::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        Log.d("TOKEN", loginResponse!!.access)


                    }
                })

        }
    }
}
