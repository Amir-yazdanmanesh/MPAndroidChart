package com.xxmassdeveloper.mpchartexample.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.xxmassdeveloper.mpchartexample.R
import com.xxmassdeveloper.mpchartexample.databinding.FragSimpleLineBinding

class ComplexityFragment : SimpleFragment() {
    private lateinit var chart: LineChart
    private var _binding: FragSimpleLineBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragSimpleLineBinding.inflate(inflater, container, false)
        val view = binding.root
        chart = binding.lineChart1
        chart.getDescription().isEnabled = false
        chart.setDrawGridBackground(false)
        chart.setData(complexity)
        chart.animateX(3000)
        val tf = Typeface.createFromAsset(requireActivity().assets, "OpenSans-Light.ttf")
        val l = chart.getLegend()
        l.typeface = tf
        val leftAxis = chart.getAxisLeft()
        leftAxis.typeface = tf
        chart.getAxisRight().isEnabled = false
        val xAxis = chart.getXAxis()
        xAxis.isEnabled = false
        return view
    }

    companion object {
        fun newInstance(): Fragment {
            return ComplexityFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}