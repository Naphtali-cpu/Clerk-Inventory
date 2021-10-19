package com.example.merchant.navigationbaractivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merchant.dashboardclass.Profile
import com.example.merchant.R
import com.example.merchant.adapters.BuyerAdapter
import com.example.merchant.adapters.MyAdapter
import com.example.merchant.api.ApiInterface
import com.example.merchant.dashboardclass.BASE_URL
import com.example.merchant.models.Buyers
import com.example.merchant.models.MyDataItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_item_in_stock.*
import kotlinx.android.synthetic.main.activity_item_in_stock.progressBar
import kotlinx.android.synthetic.main.activity_supplies.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Buyer : AppCompatActivity() {
    lateinit var myAdapter: BuyerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplies)

        recyclerViewBuyers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewBuyers.layoutManager = linearLayoutManager
        getMyData()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView.selectedItemId = R.id.suppliers
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile -> {
                    startActivity(
                        Intent(
                            applicationContext, Profile::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.suppliers -> return@OnNavigationItemSelectedListener true
                R.id.dashboard -> {
                    startActivity(
                        Intent(
                            applicationContext, Dashboard::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.products -> {
                    startActivity(
                        Intent(
                            applicationContext, Products::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }



                R.id.delivery -> {
                    startActivity(
                        Intent(
                            applicationContext, Delivery::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

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

        val retrofitData = retrofitBuilder.getBuyers("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0NzExNjEwLCJqdGkiOiIyZmVkYzMxOTc4ZmQ0MWNjYmZhNTdmZDFlYWY4Mjc0NyIsInVzZXJfaWQiOjEzLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpLm1ha29yaUBzdHVkZW50Lm1vcmluZ2FzY2hvb2wuY29tIn0.PAPiwM48Pb23ch2aEX3Ji6usqfVnJMf85oo5pMKc0Yc").enqueue(object :
            Callback<List<Buyers>> {
            override fun onResponse(call: Call<List<Buyers>>, response: Response<List<Buyers>>) {
                hideProgressBar()
                if(response?.body() != null) {
                    myAdapter = BuyerAdapter(baseContext, response.body()!!)
                    recyclerViewBuyers.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()
                }
//                val responseBody = response.body()
            }

            override fun onFailure(call: Call<List<Buyers>>, t: Throwable) {
                hideProgressBar()
                Log.d("ItemInStock", "onFailure:" + t.message)
            }
        })
    }

    private fun hideProgressBar() {
        progressBar.setVisibility(View.GONE)
    }

}