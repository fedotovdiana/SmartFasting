package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_diet_info.*

class DietInfoFragment : BaseFragment<DietInfoViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diet_info, container, false)
    }

    override fun inject() {
        AppInjector.initDietInfoComponent()
        AppInjector.injectDietInfoFragment(this)
    }

    override fun initViews() {
        viewModel.setDiet(requireArguments().getInt(DIET_PLAN_ID))
        btn_choose.setOnClickListener { viewModel.chooseDiet() }
    }

    override fun subscribe(viewModel: DietInfoViewModel) {
        observe(viewModel.diet, Observer {
            tv_fast_info.text = it.desc
            btn_choose.background = it.gradient
            activity?.toolbar?.title = it.title
            activity?.toolbar?.setBackgroundColor(it.color)
            iv_timer.setImageResource(it.img)
        })
    }

    override fun onStop() {
        super.onStop()
        activity?.toolbar?.setBackgroundColor(viewModel.getDefaultColor())
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearDietInfoComponent()
    }

    companion object {
        const val DIET_PLAN_ID = "smartfasting.diet_plan"
    }
}
