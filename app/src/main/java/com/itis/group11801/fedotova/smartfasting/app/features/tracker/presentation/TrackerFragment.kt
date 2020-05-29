package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.base.BaseFragment
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation.tracker.TrackerState.RUNNING
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
                btn_set_up.visibility = View.GONE
                tv_text_start.visibility = View.GONE
                cl.visibility = View.VISIBLE
                when (state) {
                    RUNNING -> {
                        fab_start.visibility = View.GONE
                        fab_stop.visibility = View.VISIBLE
                        tv_open_diets.visibility = View.GONE
                        tv_end_time.visibility = View.VISIBLE
                        tv_text_status.text = viewModel.getStartText()
                    }
                    else -> {
                        fab_start.visibility = View.VISIBLE
                        fab_stop.visibility = View.GONE
                        tv_open_diets.visibility = View.VISIBLE
                        tv_end_time.visibility = View.GONE
                        tv_text_status.text = viewModel.getStopText()
                    }
                }
            } else {
                btn_set_up.visibility = View.VISIBLE
                tv_text_start.visibility = View.VISIBLE
                cl.visibility = View.GONE
                fab_start.visibility = View.GONE
                fab_stop.visibility = View.GONE
                tv_open_diets.visibility = View.GONE
                ll.visibility = View.GONE
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
