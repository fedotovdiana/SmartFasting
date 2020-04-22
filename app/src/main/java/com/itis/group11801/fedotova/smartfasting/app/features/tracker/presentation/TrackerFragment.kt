package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.fragment_diet_tracker.*
import javax.inject.Inject

class TrackerFragment : Fragment() {

    @Inject
    lateinit var viewModel: TrackerViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initTrackerComponent()
        AppInjector.injectTrackerFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diet_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_choose_fast.setOnClickListener { viewModel.openDietPlan() }
    }

    //    Test SharedPreference
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val str = sharedPreferences.getString("dietPlanId", "")
        btn_choose_fast.text = str
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearTrackerComponent()
    }
}
