package com.example.merchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.merchant.addition.AddItemsInStock
import com.example.merchant.addition.AddItemsReceived
import com.example.merchant.api.ApiInterface
import com.example.merchant.models.MyDataItem
import kotlinx.android.synthetic.main.activity_item_in_stock.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://stockinvent.herokuapp.com/"
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

        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                hideProgressBar()
                val responseBody = response.body()!!

                myAdapter = MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                recyclerViewItemInStock.adapter = myAdapter

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                hideProgressBar()
                Log.d("ItemInStock", "onFailure:" + t.message)
            }
        })
    }

    private fun hideProgressBar() {
        progressBar.setVisibility(View.GONE)
    }
}