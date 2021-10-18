package com.example.merchant.navigationbaractivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merchant.dashboardclass.Profile
import com.example.merchant.R
import com.example.merchant.adapters.MyAdapter
import com.example.merchant.addition.AddProducts
import com.example.merchant.api.ApiInterface
import com.example.merchant.dashboardclass.BASE_URL
import com.example.merchant.models.MyDataItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_item_in_stock.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Products : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        recyclerViewItemInStock.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewItemInStock.layoutManager = linearLayoutManager
        getMyData()

//        val addproduct = findViewById(R.id.addproduct) as ImageView
//        addproduct.setOnClickListener{
//            val intent = Intent(this, AddProducts::class.java)
//            startActivity(intent)
//        }


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView.selectedItemId = R.id.products
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
                R.id.products -> return@OnNavigationItemSelectedListener true
                R.id.suppliers -> {
                    startActivity(
                        Intent(
                            applicationContext, Supplies::class.java
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

        val retrofitData = retrofitBuilder.getData("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0NjI2MjI1LCJqdGkiOiJlZWFhYmRmMWI4YzU0OTJjYWMwYzM2YWJmNTRiOTVlZSIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.pUmHhL4LaM8EPw6XKfn6bP3X83BwIUXBLtL5f-yzfMg").enqueue(object :
            Callback<List<MyDataItem>> {
            override fun onResponse(call: Call<List<MyDataItem>>, response: Response<List<MyDataItem>>) {
                hideProgressBar()
                if(response?.body() != null) {
                    myAdapter = MyAdapter(baseContext, response.body()!!)
                    recyclerViewItemInStock.adapter = myAdapter
                    myAdapter.notifyDataSetChanged()
                }
//                val responseBody = response.body()
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
}