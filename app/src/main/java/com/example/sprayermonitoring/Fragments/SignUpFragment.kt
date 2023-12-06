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
import android.widget.Toast
import com.example.sprayermonitoring.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignUpFragment : Fragment() {
    lateinit var mEmail : EditText
    lateinit var mPassword : EditText
    lateinit var mUsername : EditText

    lateinit var mRegistered : Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var fStore : FirebaseFirestore
    lateinit var userID : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_sign_up, container, false)

        mRegistered= view.findViewById(R.id.register)
        mPassword = view.findViewById(R.id.new_pass)
        mUsername = view.findViewById(R.id.new_username)
        mEmail = view.findViewById(R.id.new_email)
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
                    Toast.makeText(context,"Account Created", Toast.LENGTH_SHORT).show()
                    userID = mAuth.currentUser?.uid.toString()
                    val user : Map<String,String> = mapOf(
                        "fName" to "$username","email" to "$email"
                    )

                    fStore.collection("users").document(userID).set(user)
                    val fragment = LoginFragment()
                    requireFragmentManager().beginTransaction().replace(R.id.container,fragment).commit()
//                    fragmentManager?.beginTransaction()
//                        ?.replace(R.id.container2, fragment)
//                        ?.commit()
//                    startActivity(Intent(context,LoginFragment::class.java))

                }
                else
                {
                    Toast.makeText(context,"Account with this email  already exist", Toast.LENGTH_SHORT).show()
                }
            }

        }

        return view
    }

}