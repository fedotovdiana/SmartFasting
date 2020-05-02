package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.DIET_PLAN_ID
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_diet_info.*
import javax.inject.Inject

class DietInfoFragment : Fragment() {

    @Inject
    lateinit var viewModel: DietInfoViewModel

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
        viewModel.setDiet(arguments?.getInt(DIET_PLAN_ID) ?: 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_choose.setOnClickListener { viewModel.chooseDiet() }
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
