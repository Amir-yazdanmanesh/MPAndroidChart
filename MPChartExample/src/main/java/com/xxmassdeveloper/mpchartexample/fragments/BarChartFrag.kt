package com.xxmassdeveloper.mpchartexample.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.xxmassdeveloper.mpchartexample.R
import com.xxmassdeveloper.mpchartexample.custom.MyMarkerView
import com.xxmassdeveloper.mpchartexample.databinding.FragSimpleBarBinding
import com.xxmassdeveloper.mpchartexample.databinding.FragSimpleLineBinding

class BarChartFrag : SimpleFragment(), OnChartGestureListener {
    private lateinit var chart: BarChart
    private var _binding: FragSimpleBarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragSimpleBarBinding.inflate(inflater, container, false)
        val view = binding.root

        // create a new chart object
        chart = BarChart(activity)
        chart.description.isEnabled = false
        chart.onChartGestureListener = this
        val mv = MyMarkerView(activity, R.layout.custom_marker_view)
        mv.chartView = chart // For bounds control
        chart.marker = mv
        chart.setDrawGridBackground(false)
        chart.setDrawBarShadow(false)
        val tf = Typeface.createFromAsset(requireActivity().assets, "OpenSans-Light.ttf")
        chart.data = generateBarData(1, 20000f, 12)
        val l = chart.legend
        l.typeface = tf
        val leftAxis = chart.axisLeft
        leftAxis.typeface = tf
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
        chart.axisRight.isEnabled = false
        val xAxis = chart.xAxis
        xAxis.isEnabled = false

        // programmatically add the chart
        val parent = binding.parentLayout
        parent.addView(chart)
        return view
    }

    override fun onChartGestureStart(me: MotionEvent, lastPerformedGesture: ChartGesture) {
        Log.i("Gesture", "START")
    }

    override fun onChartGestureEnd(me: MotionEvent, lastPerformedGesture: ChartGesture) {
        Log.i("Gesture", "END")
        chart.highlightValues(null)
    }

    override fun onChartLongPressed(me: MotionEvent) {
        Log.i("LongPress", "Chart long pressed.")
    }

    override fun onChartDoubleTapped(me: MotionEvent) {
        Log.i("DoubleTap", "Chart double-tapped.")
    }

    override fun onChartSingleTapped(me: MotionEvent) {
        Log.i("SingleTap", "Chart single-tapped.")
    }

    override fun onChartFling(
        me1: MotionEvent,
        me2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ) {
        Log.i("Fling", "Chart fling. VelocityX: $velocityX, VelocityY: $velocityY")
    }

    override fun onChartScale(me: MotionEvent, scaleX: Float, scaleY: Float) {
        Log.i("Scale / Zoom", "ScaleX: $scaleX, ScaleY: $scaleY")
    }

    override fun onChartTranslate(me: MotionEvent, dX: Float, dY: Float) {
        Log.i("Translate / Move", "dX: $dX, dY: $dY")
    }

    companion object {
        fun newInstance(): Fragment {
            return BarChartFrag()
        }
    }
}