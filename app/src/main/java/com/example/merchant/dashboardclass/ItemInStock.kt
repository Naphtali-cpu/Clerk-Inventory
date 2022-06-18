package com.example.merchant.dashboardclass

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merchant.adapters.MyAdapter
import com.example.merchant.R
import com.example.merchant.addition.AddItemsInStock
import com.example.merchant.api.ApiInterface
import com.example.merchant.data.models.LoginResponse
import com.example.merchant.data.models.MyDataItem
import com.example.merchant.navigationbaractivities.Dashboard
import com.example.merchant.sessions.SessionManager
import kotlinx.android.synthetic.main.activity_item_in_stock.*
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://one-stocks.herokuapp.com/"

class ItemInStock : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var sessionManager: SessionManager
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_in_stock)


        recyclerViewItemInStock.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewItemInStock.layoutManager = linearLayoutManager
        getMyData()

        val stock = findViewById(R.id.itemstock) as ImageView
        stock.setOnClickListener{
            val intent = Intent(this, AddItemsInStock::class.java)
            startActivity(intent)
        }



        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
//Get list of produts

    private fun getMyData() {
        val okhttpHttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder().addInterceptor(
            okhttpHttpLoggingInterceptor
        )

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .build()
            .create(ApiInterface::class.java)
        sessionManager = SessionManager(this)

        val retrofitData = retrofitBuilder.getData("Bearer ${sessionManager.fetchAuthToken()}").enqueue(object : Callback<List<MyDataItem>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<MyDataItem>>, response: Response<List<MyDataItem>>) {
                hideProgressBar()
                if(response.code() == 200) {
                    myAdapter = MyAdapter(baseContext, response.body()!!)
                    recyclerViewItemInStock.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()

                } else if (response.code() == 401 || response.code() == 403) {
                    val newTokenResponse = getUpdatedToken()
                    sessionManager.saveAuthToken(
                        newTokenResponse.access
                    )
                }
            }

            override fun onFailure(call: Call<List<MyDataItem>>, t: Throwable) {
                hideProgressBar()
                Log.d("ItemInStock", "onFailure:" + t.message)
            }
        })

    }


    private fun hideProgressBar() {
        progressBar.setVisibility(View.GONE)
    }

    private fun getUpdatedToken(): LoginResponse {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val refreshToken = sessionManager.fetchRefreshToken()
        val service = retrofit.create(ApiInterface::class.java).refreshToken(refreshToken!!)
        Log.i("AUTH", service.toString())
        return service

    }


}


