package com.example.sprayermonitoring.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sprayermonitoring.Fragments.LoginFragment
import com.example.sprayermonitoring.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    lateinit var mEmail : EditText
    lateinit var mPassword : EditText
    lateinit var mUsername : EditText

    lateinit var mRegistered : Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var fStore : FirebaseFirestore
    lateinit var userID : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mRegistered= findViewById(R.id.register)
        mPassword = findViewById(R.id.new_pass)
        mUsername = findViewById(R.id.new_username)
        mEmail = findViewById(R.id.new_email)
        mAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        mRegistered.setOnClickListener {
            var email : String = mEmail.text.toString().trim()
            var password : String = mPassword.text.toString().trim()
            var username : String = mUsername.text.toString()

            if(TextUtils.isEmpty(email))
            {
                mEmail.setError("Email is Required")
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password))
            {
                mPassword.setError("Password is required")
                return@setOnClickListener
            }

            /// Registering user in firebase

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful)
                {
                    Toast.makeText(this,"Account Created", Toast.LENGTH_SHORT).show()
                    userID = mAuth.currentUser?.uid.toString()
                    val user : Map<String,String> = mapOf(
                        "fName" to "$username","email" to "$email"
                    )

                    fStore.collection("users").document(userID).set(user)

                    startActivity(Intent(this,LoginActivity::class.java))

                }
                else
                {
                    Toast.makeText(this,"Account with this email  already exist", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}