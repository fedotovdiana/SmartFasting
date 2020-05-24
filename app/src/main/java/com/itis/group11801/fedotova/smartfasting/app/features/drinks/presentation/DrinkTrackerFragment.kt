package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.content_drink_tracker.*
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
        super.onViewCreated(view, savedInstanceState)
        btn_add_drink.setOnClickListener { viewModel.openDialog() }
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            progress_drink.progress = it
            tv_drink.text = "$it ml"
        })
        viewModel.progressMax.observe(viewLifecycleOwner, Observer {
            progress_drink.max = it
            tv_goal.text = "$it ml"
        })
        viewModel.progressTextRemain.observe(viewLifecycleOwner, Observer {
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
}
