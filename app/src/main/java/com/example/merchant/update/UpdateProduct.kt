package com.example.merchant.update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.merchant.R
import com.example.merchant.api.ApiInterface
import com.example.merchant.api.RetrofitClient
import com.example.merchant.dashboardclass.BASE_URL
import com.example.merchant.dashboardclass.ItemInStock
import com.example.merchant.data.models.MyDataItem
import kotlinx.android.synthetic.main.activity_update_product.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpdateProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)
        val bundle: Bundle? = intent.extras


            val id = intent.getIntExtra(ARG_ITEM_ID, 6)
            initUpdateButton(id)



        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, ItemInStock::class.java)
            startActivity(intent)
        }

    }

    private fun initUpdateButton(id: Int) {

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

        buttonUpdate.setOnClickListener {
            RetrofitClient.instance.updateStock(id, name.text.toString(), slug.text.toString(), sortno.text.toString())
                .enqueue(object: Callback<MyDataItem> {
                    override fun onFailure(call: Call<MyDataItem>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<MyDataItem>, response: Response<MyDataItem>) {
                        Toast.makeText(applicationContext, "Updated !", Toast.LENGTH_LONG).show()
                    }

                })
        }


    }
    companion object {

        const val ARG_ITEM_ID = "item_id"
    }
}

