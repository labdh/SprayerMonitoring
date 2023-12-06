package com.example.sprayermonitoring.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sprayermonitoring.Activity.DashboardActivity
import com.example.sprayermonitoring.R
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    lateinit var mEmail : EditText
    lateinit var mPassword : EditText
    lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View  = inflater.inflate(R.layout.fragment_login, container, false)

        var mlogin : Button = View.findViewById(R.id.login)
        var reg : TextView = View.findViewById(R.id.newUser)
        mPassword = View.findViewById(R.id.login_pass)
        mEmail = View.findViewById(R.id.login_email)
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
                startActivity(Intent(context,DashboardActivity::class.java))
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnFailureListener {
                Toast.makeText(context,"Type email and password correctly", Toast.LENGTH_SHORT).show()
            }

        }

        reg.setOnClickListener {
            val fragment = SignUpFragment()
            requireFragmentManager().beginTransaction().replace(R.id.container,fragment).commit()

//            fragmentManager?.beginTransaction()
//                ?.replace(R.id.container1, fragment)
//                ?.commit()
//            startActivity(Intent(context, SignUpFragment::class.java))
        }


        return  View
    }


}