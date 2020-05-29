package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.tracker.TrackerState.RUNNING
import com.itis.group11801.fedotova.smartfasting.app.ui.base.BaseFragment
import com.itis.group11801.fedotova.smartfasting.app.ui.utils.hide
import com.itis.group11801.fedotova.smartfasting.app.ui.utils.show
import kotlinx.android.synthetic.main.content_tracker.*
import kotlinx.android.synthetic.main.fragment_tracker.*

class TrackerFragment : BaseFragment<TrackerViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tracker, container, false)
    }

    override fun inject() {
        AppInjector.initTrackerComponent()
        AppInjector.injectTrackerFragment(this)
    }

    override fun initViews() {
        fab_start.setOnClickListener {
            viewModel.startTimer()
        }
        fab_stop.setOnClickListener {
            viewModel.openDialog()
        }
        tv_open_diets.setOnClickListener {
            viewModel.openDiets()
        }
        btn_set_up.setOnClickListener {
            viewModel.openDiets()
        }
        tv_goal.text = viewModel.getTimerLength()
    }

    override fun subscribe(viewModel: TrackerViewModel) {
        observe(viewModel.progress, Observer {
            progress_countdown.progress = it
        })
        observe(viewModel.progressMax, Observer {
            progress_countdown.max = it
        })
        observe(viewModel.progressText, Observer {
            tv_countdown.text = it
        })
        observe(viewModel.endTime, Observer {
            tv_end_time.text = it
        })
        observe(viewModel.trackerState, Observer { state ->
            if (viewModel.checkDietAdded()) {
                btn_set_up.hide()
                tv_text_start.hide()
                cl.show()
                when (state) {
                    RUNNING -> {
                        fab_start.hide()
                        fab_stop.show()
                        tv_open_diets.hide()
                        tv_end_time.show()
                        tv_text_status.text = viewModel.getStartText()
                    }
                    else -> {
                        fab_start.show()
                        fab_stop.hide()
                        tv_open_diets.show()
                        tv_end_time.hide()
                        tv_text_status.text = viewModel.getStopText()
                    }
                }
            } else {
                btn_set_up.show()
                tv_text_start.show()
                cl.hide()
                fab_start.hide()
                fab_stop.hide()
                tv_open_diets.hide()
                ll.hide()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeTimer()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pauseTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearTrackerComponent()
    }
}
