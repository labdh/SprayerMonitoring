package com.example.sprayermonitoring.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import com.example.sprayermonitoring.Fragments.LoginFragment
import com.example.sprayermonitoring.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val splashDuration: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed({
            var mainIntent : Intent
            if(islogging()){
                mainIntent=Intent(this, DashboardActivity::class.java )
            }
            else mainIntent=Intent(this, LoginActivity::class.java )
            startActivity(mainIntent)
            finish()
        }, splashDuration)
    }

   fun islogging() : Boolean {
       val currentUser = FirebaseAuth.getInstance().currentUser
       return currentUser != null
    }
}

