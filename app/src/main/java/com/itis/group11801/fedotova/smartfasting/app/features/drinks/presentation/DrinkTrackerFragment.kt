package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.base.BaseFragment
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.content_drink_tracker.*
import kotlinx.android.synthetic.main.fragment_drink_tracker.*

class DrinkTrackerFragment : BaseFragment<DrinkTrackerViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drink_tracker, container, false)
    }

    override fun inject() {
        AppInjector.initDrinkComponent()
        AppInjector.injectDrinkTrackerFragment(this)
    }

    override fun initViews() {
        btn_add_drink.setOnClickListener { viewModel.openDialog() }
    }

    override fun subscribe(viewModel: DrinkTrackerViewModel) {
        observe(viewModel.progress, Observer {
            progress_drink.progress = it
            tv_drink.text = "$it " + ML
        })
        observe(viewModel.progressMax, Observer {
            progress_drink.max = it
            tv_goal.text = "$it " + ML
        })
        observe(viewModel.progressTextRemain, Observer {
            tv_drink_remain.text = it
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearDrinkComponent()
    }

    companion object {
        const val ML = "ml"
    }
}
