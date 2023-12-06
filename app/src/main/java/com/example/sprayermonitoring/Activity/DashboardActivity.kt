package com.example.sprayermonitoring.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.sprayermonitoring.Fragments.HistoryFragment
import com.example.sprayermonitoring.Fragments.HomeFragment
import com.example.sprayermonitoring.Fragments.LoginFragment
import com.example.sprayermonitoring.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity(){
//    lateinit var navigationview:NavigationView
    var pressedTime : Long = 0
    private lateinit var drawerLayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        frag(HomeFragment(),R.id.dashboard_frag)

        drawerLayout=findViewById(R.id.container)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navigationview = findViewById<NavigationView>(R.id.nav_view)
        val menu = findViewById<ImageView>(R.id.menu)

        menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationview.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_home -> {
                    frag(HomeFragment(),R.id.dashboard_frag)
                    closedrawer()
                }
//                R.id.nav_aboutus -> frag(AboutFragment)
                R.id.nav_history -> {
                    frag(HistoryFragment(),R.id.dashboard_frag)
                    closedrawer()
                }
                R.id.nav_logout -> signout()
            }
            true
        }


    }

    private fun signout() {
        FirebaseAuth.getInstance().signOut()
        closedrawer()
        startActivity(Intent(this,LoginActivity::class.java))
    }

    private fun frag(fragment: Fragment,id: Int) {
        supportFragmentManager.beginTransaction().replace(id,fragment).commit()
    }
//
    @Override
    public override fun onBackPressed() {
            closedrawer()
            if(pressedTime+2000>System.currentTimeMillis()){
                super.onBackPressed()
                finish()
            }
            else{
                Toast.makeText(this,"Press back again to exit",Toast.LENGTH_SHORT).show()
            }
            pressedTime = System.currentTimeMillis()
    }

    fun closedrawer(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START)
    }
//    override fun onPause() {
//        super.onPause()
//        closedrawer()
//    }
}