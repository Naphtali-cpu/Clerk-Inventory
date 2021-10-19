package com.example.merchant.dashboardclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merchant.adapters.MyAdapter
import com.example.merchant.R
import com.example.merchant.addition.AddItemsInStock
import com.example.merchant.api.ApiInterface
import com.example.merchant.models.MyDataItem
import com.example.merchant.navigationbaractivities.Dashboard
import kotlinx.android.synthetic.main.activity_item_in_stock.*
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

//        val next = findViewById(R.id.c2) as CardView
//        next.setOnClickListener{
//            val intent = Intent(this, ItemDetails::class.java)
//            startActivity(intent)
//        }


        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
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

        val retrofitData = retrofitBuilder.getData("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM0Njk3ODcyLCJqdGkiOiI2ZGY1MDdiNjUxNDk0MDNjYTBmMTkyNzliYWE3YzM0YSIsInVzZXJfaWQiOjEyLCJyb2xlIjoiU1VQUExJRVIiLCJ1c2VybmFtZSI6Im5hcGh0YWxpOTE5QGdtYWlsLmNvbSJ9.CiVdQD3JG4R_HOUkGPXEpfJPu18jwX5Bc5qFYqb04IU").enqueue(object : Callback<List<MyDataItem>> {
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