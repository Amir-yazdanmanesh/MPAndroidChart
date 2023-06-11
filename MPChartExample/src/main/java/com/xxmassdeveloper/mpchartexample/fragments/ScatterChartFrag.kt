package com.xxmassdeveloper.mpchartexample.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.xxmassdeveloper.mpchartexample.R
import com.xxmassdeveloper.mpchartexample.custom.MyMarkerView
import com.xxmassdeveloper.mpchartexample.databinding.FragSimplePieBinding
import com.xxmassdeveloper.mpchartexample.databinding.FragSimpleScatterBinding

class ScatterChartFrag : SimpleFragment() {
    private lateinit var chart: ScatterChart
    private var _binding: FragSimpleScatterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragSimpleScatterBinding.inflate(inflater, container, false)
        val view = binding.root
        chart = binding.scatterChart1
        chart.getDescription().isEnabled = false
        val tf = Typeface.createFromAsset(requireActivity().assets, "OpenSans-Light.ttf")
        val mv = MyMarkerView(activity, R.layout.custom_marker_view)
        mv.chartView = chart // For bounds control
        chart.setMarker(mv)
        chart.setDrawGridBackground(false)
        chart.setData(generateScatterData(6, 10000f, 200))
        val xAxis = chart.getXAxis()
        xAxis.isEnabled = true
        xAxis.position = XAxisPosition.BOTTOM
        val leftAxis = chart.getAxisLeft()
        leftAxis.typeface = tf
        val rightAxis = chart.getAxisRight()
        rightAxis.typeface = tf
        rightAxis.setDrawGridLines(false)
        val l = chart.getLegend()
        l.isWordWrapEnabled = true
        l.typeface = tf
        l.formSize = 14f
        l.textSize = 9f

        // increase the space between legend & bottom and legend & content
        l.yOffset = 13f
        chart.setExtraBottomOffset(16f)
        return view
    }

    companion object {
        fun newInstance(): Fragment {
            return ScatterChartFrag()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}