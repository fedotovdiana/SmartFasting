package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.features.tracker.domain.TimerState.RUNNING
import kotlinx.android.synthetic.main.content_tracker.*
import kotlinx.android.synthetic.main.fragment_tracker.*
import javax.inject.Inject

class TrackerFragment : Fragment() {

    @Inject
    lateinit var viewModel: TrackerViewModel

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
        return inflater.inflate(R.layout.fragment_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeUI()
        fab_start.setOnClickListener {
            viewModel.startTimer()
        }
        fab_stop.setOnClickListener {
            viewModel.openDialog()
        }
        tv_open_diets.setOnClickListener {
            viewModel.openDiets()
        }
        tv_goal.text = viewModel.getTimerLength()
    }

    private fun subscribeUI() {
        viewModel.timerState.observe(viewLifecycleOwner, Observer { state ->
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
        })
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            progress_countdown.progress = it
        })
        viewModel.progressMax.observe(viewLifecycleOwner, Observer {
            progress_countdown.max = it
        })
        viewModel.progressText.observe(viewLifecycleOwner, Observer {
            tv_countdown.text = it
        })
        viewModel.startTime.observe(viewLifecycleOwner, Observer {
            tv_end_time.text = it
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
