package com.example.merchant.navigationbaractivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.merchant.*
import com.example.merchant.dashboardclass.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.hdodenhof.circleimageview.CircleImageView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
//        For all the cards
        val image = findViewById(R.id.profile) as CircleImageView
        val card1 = findViewById(R.id.c1) as CardView
        val card2 = findViewById(R.id.c2) as CardView
        val card3 = findViewById(R.id.c3) as CardView
        val card4 = findViewById(R.id.c4) as CardView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_bar)
        bottomNavigationView.selectedItemId = R.id.dashboard
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
                R.id.dashboard -> return@OnNavigationItemSelectedListener true
                R.id.suppliers -> {
                    startActivity(
                        Intent(
                            applicationContext, Buyer::class.java
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


//        Cards section
        image.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        card1.setOnClickListener{
            val intent = Intent(this, Analysis::class.java)
            startActivity(intent)
        }
        card2.setOnClickListener{
            val intent = Intent(this, ItemsReceived::class.java)
            startActivity(intent)
        }
        card3.setOnClickListener{
            val intent = Intent(this, Expired::class.java)
            startActivity(intent)
        }
        card4.setOnClickListener{
            val intent = Intent(this, ItemInStock::class.java)
            startActivity(intent)
        }

    }
}