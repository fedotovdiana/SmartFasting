package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.di.Injectable
import com.itis.group11801.fedotova.smartfasting.di.injectViewModel
import com.itis.group11801.fedotova.smartfasting.viewmodel.TrackerViewModel
import kotlinx.android.synthetic.main.fragment_diet_tracker.*
import javax.inject.Inject

class TrackerFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TrackerViewModel

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
        val root = inflater.inflate(R.layout.fragment_diet_tracker, container, false)
        viewModel = injectViewModel(viewModelFactory)
        return root
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
