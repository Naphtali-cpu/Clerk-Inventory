package com.example.merchant.addition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.merchant.Dashboard
import com.example.merchant.R
import com.example.merchant.api.RetrofitClient
import com.example.merchant.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_add_items_in_stock.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddItemsInStock : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_items_in_stock)

        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

//        addstock.setOnClickListener {
//
//            val name = editTextEmail.text.toString().trim()
//            val slug = editTextPassword.text.toString().trim()
//
//
//            if(name.isEmpty()){
//                editTextEmail.error = "Email required"
//                editTextEmail.requestFocus()
//                return@setOnClickListener
//            }
//
//
//            if(slug.isEmpty()){
//                editTextPassword.error = "Password required"
//                editTextPassword.requestFocus()
//                return@setOnClickListener
//            }
//
//
//
//            RetrofitClient.instance.addProduct(name, slug)
//                .enqueue(object: Callback<DefaultResponse> {
//                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
//                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                    }
//
//                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
//                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
//                    }
//
//                })
//
//        }
    }
}