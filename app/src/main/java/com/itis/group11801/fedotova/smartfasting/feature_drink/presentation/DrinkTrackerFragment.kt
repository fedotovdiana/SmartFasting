package com.itis.group11801.fedotova.smartfasting.feature_drink.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.fragment_drink_tracker.*
import javax.inject.Inject

class DrinkTrackerFragment : Fragment() {

    @Inject
    lateinit var viewModel: DrinkTrackerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initDrinkComponent()
        AppInjector.injectDrinkTrackerFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drink_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_add_drink.setOnClickListener {
            viewModel.openDrinkDialog()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearDrinkComponent()
    }
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
