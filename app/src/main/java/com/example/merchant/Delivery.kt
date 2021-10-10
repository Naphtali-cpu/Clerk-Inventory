package com.example.merchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.merchant.addition.AddDelivery
import com.google.android.material.bottomnavigation.BottomNavigationView

class Delivery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        val addel = findViewById(R.id.adddelivery) as ImageView
        addel.setOnClickListener{
            val intent = Intent(this, AddDelivery::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView.selectedItemId = R.id.delivery
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.delivery -> return@OnNavigationItemSelectedListener true
                R.id.suppliers -> {
                    startActivity(
                        Intent(
                            applicationContext, Supplies::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.products -> {
                    startActivity(
                        Intent(
                            applicationContext, Products::class.java
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
            }
            false
        })
    }
}