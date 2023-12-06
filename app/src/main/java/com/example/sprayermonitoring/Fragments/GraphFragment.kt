package com.example.sprayermonitoring.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sprayermonitoring.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.MPPointF


class GraphFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_graph, container, false)
//        var lineChart = view.findViewById<com.github.mikephil.charting.charts.LineChart>(R.id.lineChart)
//        //Part1
//        val entries = ArrayList<Entry>()
//
////Part2
//        entries.add(Entry(1f, 10f))
//        entries.add(Entry(2f, 2f))
//        entries.add(Entry(3f, 7f))
//        entries.add(Entry(4f, 20f))
//        entries.add(Entry(5f, 16f))
//
////Part3
//        val vl = LineDataSet(entries, "My Type")
//
////Part4
//        vl.setDrawValues(false)
//        vl.setDrawFilled(true)
//        vl.lineWidth = 3f
//        vl.fillColor = R.color.gray
//        vl.fillAlpha = R.color.red
//
////Part5
//        if(lineChart!=null) lineChart.setNoDataText("Pls Pass data")
//        lineChart.xAxis.labelRotationAngle = 0f
//
////Part6
//        lineChart.data = LineData(vl)
//
////Part7
//        lineChart.axisRight.isEnabled = false
////        lineChart.xAxis.axisMaximum = j+0.1f
//
////Part8
//        lineChart.setTouchEnabled(true)
//        lineChart.setPinchZoom(true)
//
////Part9
//        lineChart.description.text = "Days"
//        lineChart.setNoDataText("No forex yet!")
//
////Part10
//        lineChart.animateX(1800, Easing.EaseInExpo)

//Part11
//        val markerView = context?.let { CustomMarker(it, R.layout.marker_view) }
//        lineChart.marker = markerView
        return view
    }

}

//class CustomMarker(any: Any, markerView: Any) {
//
//}
//class CustomMarker(context: Context, layoutResource: Int):  MarkerView(context, layoutResource) {
//    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
//        val value = entry?.y?.toDouble() ?: 0.0
//        var resText :String
//        if(value.toString().length > 8){
//            resText = "Val: " + value.toString().substring(0,7)
//        }
//        else{
//            resText = "Val: " + value.toString()
//        }
//        val tvPrice = findViewById<TextView>(R.id.tvPrice)
//        tvPrice.text = resText
//        super.refreshContent(entry, highlight)
//    }
//
//    override fun getOffsetForDrawingAtPoint(xpos: Float, ypos: Float): MPPointF {
//        return MPPointF(-width / 2f, -height - 10f)
//    }
//}


