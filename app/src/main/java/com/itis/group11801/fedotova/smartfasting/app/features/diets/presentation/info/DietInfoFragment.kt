package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_diet_info.*
import javax.inject.Inject

class DietInfoFragment : Fragment() {

    @Inject
    lateinit var viewModel: DietInfoViewModel

    val APP_PREFERENCES = "pref"

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initDietInfoComponent()
        AppInjector.injectDietInfoFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeViewModel()
        return inflater.inflate(R.layout.fragment_diet_info, container, false)
    }

    private fun observeViewModel() {
        viewModel.diet.observe(viewLifecycleOwner, Observer {
            tv_fast_info.text = it.desc
            btn_choose.background = it.gradient
            activity?.toolbar?.title = it.title
            activity?.toolbar?.setBackgroundColor(it.color)
        })
        viewModel.setDietPlan(arguments?.getInt("dietPlanId") ?: 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_choose.setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("dietPlanId", viewModel.diet.value?.id.toString())
                apply()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        activity?.toolbar?.setBackgroundColor(viewModel.getDefaultColor())
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearDietInfoComponent()
    }
}
