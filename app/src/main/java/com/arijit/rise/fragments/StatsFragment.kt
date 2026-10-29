package com.arijit.rise.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arijit.rise.R
import com.arijit.rise.components.FocusHoursChart

class StatsFragment : Fragment() {

    private lateinit var focusChart: FocusHoursChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        focusChart = view.findViewById(R.id.chart)
//        focusChart.post {
//            focusChart.animateChart()
//            focusChart.startLightningAnimation()
//        }

        return view
    }


}