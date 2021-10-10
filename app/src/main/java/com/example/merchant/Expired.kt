package com.example.merchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.merchant.addition.AddExpiredItems
import com.example.merchant.addition.AddItemsInStock

class Expired : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expired)
        val expire = findViewById(R.id.itemexpired) as ImageView
        expire.setOnClickListener{
            val intent = Intent(this, AddExpiredItems::class.java)
            startActivity(intent)
        }


        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}