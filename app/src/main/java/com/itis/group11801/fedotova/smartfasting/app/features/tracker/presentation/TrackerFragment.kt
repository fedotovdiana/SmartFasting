package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.TimerState.*
import kotlinx.android.synthetic.main.content_timer.*
import kotlinx.android.synthetic.main.fragment_tracker.*
import javax.inject.Inject
import kotlin.random.Random

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
        fab_start.setOnClickListener {
            viewModel.startTimer()
        }
        fab_pause.setOnClickListener {
            viewModel.pauseTimer()
        }
        fab_stop.setOnClickListener {
            viewModel.stopTimer()
        }
        tv_open_diets.setOnClickListener {
            viewModel.openDiets()
        }
        tv_open_diets.text = Random.nextInt().toString()
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.timerState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                RUNNING -> {
                    fab_start.isEnabled = false
                    fab_pause.isEnabled = true
                    fab_stop.isEnabled = true
                }
                STOPPED -> {
                    fab_start.isEnabled = true
                    fab_pause.isEnabled = false
                    fab_stop.isEnabled = false
                }
                PAUSED -> {
                    fab_start.isEnabled = true
                    fab_pause.isEnabled = false
                    fab_stop.isEnabled = true
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
            textView_countdown.text = it
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.initTimer()
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearTrackerComponent()
    }
}
