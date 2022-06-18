package com.example.merchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.merchant.auth.LoginActivity
import com.example.merchant.navigationbaractivities.Dashboard
import com.example.merchant.sessions.LoginPref
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var session: LoginPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        session = LoginPref(this)
//        Redirecting if user is logged in

        if (session.isLoggedIn()) {
            val intent = Intent(this,Dashboard :: class.java)
            startActivity(intent)
            finish()
        } else {
            val motionLayout = findViewById<MotionLayout>(R.id.motionLayout)
            motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }

                override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                }

            })
        }

    }

}