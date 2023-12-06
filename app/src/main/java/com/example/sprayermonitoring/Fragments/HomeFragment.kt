package com.example.sprayermonitoring.Fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.sprayermonitoring.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
//    private val client = OkHttpClient()
     private lateinit var lineChart: LineChart
    private lateinit var lineChart2: LineChart
    private lateinit var lineChart3: LineChart
    private val handler = Handler(Looper.getMainLooper())
    var dataEntries1 = ArrayList<Entry>()
    var dataEntries2 = ArrayList<Entry>()
    var dataEntries3 = ArrayList<Entry>()
    lateinit var runnable1 : Runnable
    lateinit var runnable2 : Runnable
    lateinit var runnable3 : Runnable
    lateinit var pressure : TextView
    lateinit var speed : TextView
    lateinit var water : TextView
    private val timer = Timer()
    var xx=0
    private var xAxisValue = 0f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        lineChart = view.findViewById<com.github.mikephil.charting.charts.LineChart>(R.id.lineChart)
        val pressurel = view.findViewById<LinearLayout>(R.id.graph_layout1)
        water = view.findViewById(R.id.flow_rate)
        speed=view.findViewById(R.id.speed)
        pressure=view.findViewById(R.id.pressure)
        val progress1=view.findViewById<CircularProgressBar>(R.id.circularProgressBar1)
        val progress2=view.findViewById<CircularProgressBar>(R.id.circularProgressBar2)
        DataUpdates1()
        progress1.apply {
            progress = 0f
            setProgressWithAnimation(46f, 1500)
            progressMax = 100f
            progressBarColor = Color.BLACK
            progressBarColorStart = Color.GRAY
            progressBarColorEnd = Color.RED
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set background ProgressBar Color
            backgroundProgressBarColor = Color.GRAY
            // or with gradient
            backgroundProgressBarColorStart = Color.WHITE
            backgroundProgressBarColorEnd = Color.RED
            backgroundProgressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set Width
            progressBarWidth = 7f // in DP
            backgroundProgressBarWidth = 3f // in DP

            // Other
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }
        progress2.apply {
            progress = 0f
            // or with animation
            setProgressWithAnimation(75f, 1500) // =1s

            // Set Progress Max
            progressMax = 100f

            // Set ProgressBar Color
            progressBarColor = Color.BLACK
            // or with gradient
            progressBarColorStart = Color.GRAY
            progressBarColorEnd = Color.RED
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set background ProgressBar Color
            backgroundProgressBarColor = Color.GRAY
            // or with gradient
            backgroundProgressBarColorStart = Color.WHITE
            backgroundProgressBarColorEnd = Color.RED
            backgroundProgressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set Width
            progressBarWidth = 7f // in DP
            backgroundProgressBarWidth = 3f // in DP

            // Other
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
//            progressMax=100f
//            setProgressWithAnimation(45f,1500)
//            progressBarWidth =5f
//            backgroundProgressBarWidth=7f
//            progressBarColor = Color.RED
        }

//        DataUpdates2()
//        DataUpdates3()
//
//        speed.setOnClickListener {
//             speedl.visibility = View.VISIBLE
//             waterl.visibility = View.INVISIBLE
//            pressurel.visibility = View.INVISIBLE
//            randomdata(dataEntries1,dataEntries2)
//        }
//        water.setOnClickListener {
//            speedl.visibility = View.INVISIBLE
//            waterl.visibility = View.VISIBLE
//            pressurel.visibility = View.INVISIBLE
//            randomdata(dataEntries1,dataEntries3)
//        }
//        pressure.setOnClickListener {
//            speedl.visibility = View.INVISIBLE
//            waterl.visibility = View.INVISIBLE
//            pressurel.visibility = View.VISIBLE
//            randomdata(dataEntries3,dataEntries2)
//        }
        return view
    }

    private fun DataUpdates1() {

        var vl :LineDataSet

        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                randomdata(dataEntries2,dataEntries3)
                if(dataEntries1.size>10){
                       dataEntries1.remove(dataEntries1.first())
                }
                vl = LineDataSet(dataEntries1, "My Type")
                vl.setDrawValues(true)
                vl.setDrawFilled(true)
                vl.setMode(LineDataSet.Mode.CUBIC_BEZIER)
                vl.fillDrawable = context?.let { ContextCompat.getDrawable(it,R.drawable.gradient_bg) }
                vl.setCircleColor(R.color.black)
                vl.setColor(R.color.black)
                vl.lineWidth = 3f
                lineChart.xAxis.labelRotationAngle = 0f
                lineChart.xAxis.granularity = 2f
                lineChart.axisRight.isEnabled = false
                lineChart.xAxis.setDrawLabels(false)
                lineChart.setTouchEnabled(true)
                lineChart.setPinchZoom(true)
                lineChart.description.text = "Days"
                lineChart.moveViewToX(dataEntries1.size.toFloat())
                lineChart.data = LineData(vl)
                updateChart()
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun DataUpdates2() {
        var vl :LineDataSet

        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                randomdata(dataEntries1,dataEntries3)
                if(dataEntries2.size>10){
                    dataEntries2.remove(dataEntries2.first())
                }
                vl = LineDataSet(dataEntries2, "My Type")
                vl.setDrawValues(true)
                vl.setDrawFilled(true)
                vl.setMode(LineDataSet.Mode.CUBIC_BEZIER)
                vl.fillDrawable = context?.let { ContextCompat.getDrawable(it,R.drawable.gradient_bg) }
                vl.setCircleColor(R.color.black)
                vl.setColor(R.color.black)
                vl.lineWidth = 3f
                lineChart2.xAxis.labelRotationAngle = 0f
                lineChart2.xAxis.granularity = 2f
                lineChart2.axisRight.isEnabled = false
                lineChart2.xAxis.setDrawLabels(false)
                lineChart2.setTouchEnabled(true)
                lineChart2.setPinchZoom(true)
                lineChart2.description.text = "Days"
                lineChart2.moveViewToX(dataEntries2.size.toFloat())
                lineChart2.data = LineData(vl)
                updateChart()
                handler.postDelayed(this, 1000)
            }
        })
    }
    private fun DataUpdates3() {
        var vl :LineDataSet

        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                randomdata(dataEntries1,dataEntries2)
                if(dataEntries3.size>10){
                    dataEntries3.remove(dataEntries3.first())
                }
                vl = LineDataSet(dataEntries3, "My Type")
                vl.setDrawValues(true)
                vl.setDrawFilled(true)
                vl.setMode(LineDataSet.Mode.CUBIC_BEZIER)
                vl.fillDrawable = context?.let { ContextCompat.getDrawable(it,R.drawable.gradient_bg) }
                vl.setCircleColor(R.color.black)
                vl.setColor(R.color.black)
                vl.lineWidth = 3f
                lineChart3.xAxis.labelRotationAngle = 0f
                lineChart3.xAxis.granularity = 2f
                lineChart3.axisRight.isEnabled = false
                lineChart3.xAxis.setDrawLabels(false)
                lineChart3.setTouchEnabled(true)
                lineChart3.setPinchZoom(true)
                lineChart3.description.text = "Days"
                lineChart3.moveViewToX(dataEntries3.size.toFloat())
                lineChart3.data = LineData(vl)
                updateChart()
                handler.postDelayed(this, 1000)
            }
        })
    }
    private fun randomdata(data1 :ArrayList<Entry>,data2 : ArrayList<Entry>){
        val randomValue1 = (0..10).shuffled()        //(Math.random().range * 14).toInt()
        val randomValue2 = (1..5).shuffled()
        val randomValue3 = (1..100).shuffled()
        pressure.text= randomValue1[0].toString()
        speed.text= randomValue2[0].toString()
        water.text= randomValue3[0].toString()
        xx+=1
        dataEntries1.add(Entry(xx.toFloat(), randomValue2[0].toFloat()))
//        dataEntries2.add(Entry(xx.toFloat(), randomValue2[0].toFloat()))
//        dataEntries3.add(Entry(xx.toFloat(), randomValue3[0].toFloat()))
//        data1.clear()
//        data2.clear()

    }
    private fun updateChart() {
        lineChart.data.notifyDataChanged()
        lineChart.invalidate()
    }


//    fun run(url: String) {
//        val Url=URLEncoder.encode("https://agrirover-flask-app.onrender.com/api/agrochemical/pressure")
//        val request = Request.Builder()
//            .url(Url)
//            .build()
//
//        client.newCall(request).enqueue(object : Callback {
//            fun onFailure(call: Call, e: IOException) {}
//            fun onResponse(call: Call, response: Response) = println(response.body()?.string())
//        })
//    }

    fun retro(url:String){
        val retroBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retroBuilder.getData()

        retrofitData.enqueue(object : retrofit2.Callback<List<String>> {
            override fun onResponse(call: retrofit2.Call<List<String>>, response: retrofit2.Response<List<String>>
            ) {
                val responsebody = response.body()
                println(responsebody)
//                view?.findViewById<TextView>(R.id.pressure).text =
            }
            override fun onFailure(call: retrofit2.Call<List<String>>, t: Throwable) {
                println("***********")
            }
        })
    }

//    private fun fetchData() {
//        //volley library
////        val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=bc818d6b7254433e96aedd000a7ff952"
//        val url= URLEncoder.encode("https://agrirover-flask-app.onrender.com/api/agrochemical/pressure")
//        //making a request
////        val jo = object :JsonObjectRequest(
////            Request.Builder().url(url).build()
////        )
//        val jsonObjectRequest = object: JsonObjectRequest(
//            Request.Method.GET,
//            url,
//            null,
//            Response.Listener {
//                val newsJsonArray = it.getJSONArray("articles")
//                val newsArray = ArrayList<News>()
//                for(i in 0 until newsJsonArray.length()) {
//                    val newsJsonObject = newsJsonArray.getJSONObject(i)
//                    val news = News(
//                        newsJsonObject.getString("title"),
//                        newsJsonObject.getString("author"),
//                        newsJsonObject.getString("url"),
//                        newsJsonObject.getString("urlToImage"),
//                        newsJsonObject.getString("publishedAt")
//                    )
//                    newsArray.add(news)
//                }
//                mAdapter.updateNews(newsArray)
//            },
//            Response.ErrorListener {
//            })
//        {
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                headers["User-Agent"] = "Mozilla/5.0"
//                return headers
//            }
//        }
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
//    }

}

