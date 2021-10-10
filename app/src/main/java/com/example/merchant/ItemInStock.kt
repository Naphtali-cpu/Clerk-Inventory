package com.example.merchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.merchant.addition.AddItemsInStock
import com.example.merchant.addition.AddItemsReceived

class ItemInStock : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_in_stock)

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
}