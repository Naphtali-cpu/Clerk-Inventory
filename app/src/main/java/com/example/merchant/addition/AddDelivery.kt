package com.example.merchant.addition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

import com.example.merchant.navigationbaractivities.Dashboard
import com.example.merchant.R
import com.example.merchant.api.RetrofitClient
import com.example.merchant.data.models.DefaultResponse
import com.example.merchant.data.models.Deliveries
import com.example.merchant.data.models.UserResponse

import kotlinx.android.synthetic.main.activity_add_delivery.*
import kotlinx.android.synthetic.main.deliverylist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDelivery : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_delivery)

        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        createdelivery.setOnClickListener {


            RetrofitClient.instance.createDelivery(id.text.toString(), supplier.text.toString(), item.text.toString(), courier.text.toString(), "string")
                .enqueue(object: Callback<Deliveries> {
                    override fun onFailure(call: Call<Deliveries>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<Deliveries>, response: Response<Deliveries>) {
                        Toast.makeText(applicationContext, "Created !", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }

}
