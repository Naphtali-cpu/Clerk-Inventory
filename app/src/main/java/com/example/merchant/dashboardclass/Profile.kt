package com.example.merchant.dashboardclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.merchant.R
import com.example.merchant.auth.LoginActivity
import com.example.merchant.navigationbaractivities.Dashboard

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val main = findViewById(R.id.back) as ImageView

        main.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        val exit = findViewById(R.id.logout) as CardView

        exit.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}