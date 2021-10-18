package com.example.merchant.dashboardclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.merchant.R
import com.example.merchant.addition.AddItemsReceived
import com.example.merchant.navigationbaractivities.Dashboard

class ItemsReceived : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_received)

        val receive = findViewById(R.id.itemreceive) as ImageView
        receive.setOnClickListener{
            val intent = Intent(this, AddItemsReceived::class.java)
            startActivity(intent)
        }

        val main = findViewById(R.id.back) as ImageView
//        val det = findViewById(R.id.item1) as CardView

        main.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

//        det.setOnClickListener{
//            val intent = Intent(this, ItemDetails::class.java)
//            startActivity(intent)
//        }
    }
}