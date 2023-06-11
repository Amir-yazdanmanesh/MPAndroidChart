package com.xxmassdeveloper.mpchartexample.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.xxmassdeveloper.mpchartexample.R
import com.xxmassdeveloper.mpchartexample.databinding.FragSimpleLineBinding
import com.xxmassdeveloper.mpchartexample.databinding.FragSimplePieBinding

class PieChartFrag : SimpleFragment() {
    private lateinit var chart: PieChart
    private var _binding: FragSimplePieBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragSimplePieBinding.inflate(inflater, container, false)
        val view = binding.root
        chart = binding.pieChart1
        chart.getDescription().isEnabled = false
        val tf = Typeface.createFromAsset(requireActivity().assets, "OpenSans-Light.ttf")
        chart.setCenterTextTypeface(tf)
        chart.setCenterText(generateCenterText())
        chart.setCenterTextSize(10f)
        chart.setCenterTextTypeface(tf)

        // radius of the center hole in percent of maximum radius
        chart.setHoleRadius(45f)
        chart.setTransparentCircleRadius(50f)
        val l = chart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        chart.setData(generatePieData())
        return view
    }

    private fun generateCenterText(): SpannableString {
        val s = SpannableString("Revenues\nQuarters 2015")
        s.setSpan(RelativeSizeSpan(2f), 0, 8, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 8, s.length, 0)
        return s
    }

    companion object {
        fun newInstance(): Fragment {
            return PieChartFrag()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}