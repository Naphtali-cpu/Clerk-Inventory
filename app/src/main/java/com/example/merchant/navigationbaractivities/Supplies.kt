package com.example.merchant.navigationbaractivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.merchant.dashboardclass.Profile
import com.example.merchant.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Supplies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplies)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView.selectedItemId = R.id.suppliers
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile -> {
                    startActivity(
                        Intent(
                            applicationContext, Profile::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.suppliers -> return@OnNavigationItemSelectedListener true
                R.id.dashboard -> {
                    startActivity(
                        Intent(
                            applicationContext, Dashboard::class.java
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



                R.id.delivery -> {
                    startActivity(
                        Intent(
                            applicationContext, Delivery::class.java
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