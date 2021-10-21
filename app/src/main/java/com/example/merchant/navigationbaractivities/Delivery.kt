package com.example.merchant.navigationbaractivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merchant.R
import com.example.merchant.adapters.BuyerAdapter
import com.example.merchant.adapters.DeliveryAdapter
import com.example.merchant.addition.AddDelivery
import com.example.merchant.api.ApiInterface
import com.example.merchant.dashboardclass.BASE_URL
import com.example.merchant.data.models.Buyers
import com.example.merchant.data.models.Deliveries
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_delivery.*
import kotlinx.android.synthetic.main.activity_item_in_stock.*
import kotlinx.android.synthetic.main.activity_supplies.*
import kotlinx.android.synthetic.main.activity_supplies.progressBar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Delivery : AppCompatActivity() {

    lateinit var myAdapter: DeliveryAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        recyclerViewDelivery.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewDelivery.layoutManager = linearLayoutManager
        getMyData()

        val addel = findViewById(R.id.adddelivery) as ImageView
        addel.setOnClickListener{
            val intent = Intent(this, AddDelivery::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView.selectedItemId = R.id.delivery
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.delivery -> return@OnNavigationItemSelectedListener true
                R.id.suppliers -> {
                    startActivity(
                        Intent(
                            applicationContext, Buyer::class.java
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

                R.id.dashboard -> {
                    startActivity(
                        Intent(
                            applicationContext, Dashboard::class.java
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

        val retrofitData = retrofitBuilder.getDelivery("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0ODUxMzE3LCJqdGkiOiJlOWNlY2MwYjcyZjA0NjVkYTYyYzkwMDNlNzdlYmQ4ZCIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.TB9VoKba1FZ_7QK10BVVVsm9dJcWYw6FfZjWhfWgYAs").enqueue(object :
            Callback<List<Deliveries>> {
            override fun onResponse(call: Call<List<Deliveries>>, response: Response<List<Deliveries>>) {
                hideProgressBar()
                if(response?.body() != null) {
                    myAdapter = DeliveryAdapter(baseContext, response.body()!!)
                    recyclerViewDelivery.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()
                }
//                val responseBody = response.body()
            }

            override fun onFailure(call: Call<List<Deliveries>>, t: Throwable) {
                hideProgressBar()
                Log.d("ItemInStock", "onFailure:" + t.message)
            }
        })
    }

    private fun hideProgressBar() {
        progressBar.setVisibility(View.GONE)
    }
}