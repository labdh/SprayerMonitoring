package com.example.sprayermonitoring.Fragments

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
     @GET("pressure")
     fun getData(): Call<List<String>>
}
