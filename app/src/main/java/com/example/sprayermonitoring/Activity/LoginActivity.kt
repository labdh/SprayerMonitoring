package com.example.sprayermonitoring.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sprayermonitoring.Fragments.SignUpFragment
import com.example.sprayermonitoring.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var mEmail : EditText
    lateinit var mPassword : EditText
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var mlogin : Button = findViewById(R.id.login)
        var reg : TextView = findViewById(R.id.newUser)
        mPassword = findViewById(R.id.login_pass)
        mEmail = findViewById(R.id.login_email)
        mAuth = FirebaseAuth.getInstance()
        mlogin.setOnClickListener {
            val email : String = mEmail.text.toString().trim()
            val password : String = mPassword.text.toString().trim()

            if(TextUtils.isEmpty(email) )
            {
                mEmail.setError("Email is Required")
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password))
            {
                mPassword.setError("Password is required")
                return@setOnClickListener
            }

            /// Authentication of user

            mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener{
//                Toast.makeText(this,"Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,DashboardActivity::class.java))
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnFailureListener {
                Toast.makeText(this,"Type email and password correctly", Toast.LENGTH_SHORT).show()
            }

        }

        reg.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
}