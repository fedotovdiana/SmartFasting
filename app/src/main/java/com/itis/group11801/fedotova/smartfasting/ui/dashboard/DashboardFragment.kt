package com.itis.group11801.fedotova.smartfasting.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.itis.group11801.fedotova.smartfasting.R

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        circularProgressBar.apply {
//            // Set Progress
//            progress = 65f
//            // or with animation
//            setProgressWithAnimation(65f, 1000) // =1s
//
//            // Set Progress Max
//            progressMax = 200f
//
//            // Set ProgressBar Color
//            progressBarColor = Color.BLACK
//            // or with gradient
//            progressBarColorStart = Color.GRAY
//            progressBarColorEnd = Color.RED
//            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
//
//            // Set background ProgressBar Color
//            backgroundProgressBarColor = Color.GRAY
//            // or with gradient
//            backgroundProgressBarColorStart = Color.WHITE
//            backgroundProgressBarColorEnd = Color.RED
//            backgroundProgressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
//
//            // Set Width
//            progressBarWidth = 7f // in DP
//            backgroundProgressBarWidth = 3f // in DP
//
//            // Other
//            roundBorder = true
//            startAngle = 180f
//            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
//        }
//    }
}