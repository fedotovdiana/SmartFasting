package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.fragment_statistics.*
import javax.inject.Inject

class StatisticsFragment : Fragment() {

    @Inject
    lateinit var viewModel: StatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initStatisticsComponent()
        AppInjector.injectStatisticsFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_to_drink_journal.setOnClickListener { viewModel.openDrinkJournal() }
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.drinkVolumeTotal.observe(viewLifecycleOwner, Observer {
            tv_stat_total_volume.text = it
        })
        viewModel.drinkVolumeAverage.observe(viewLifecycleOwner, Observer {
            tv_stat_avg_volume.text = it
        })
        viewModel.trackerNotesCount.observe(viewLifecycleOwner, Observer {
            tv_tracker_count.text = it
        })
        viewModel.trackerNotesMin.observe(viewLifecycleOwner, Observer {
            tv_tracker_min.text = it
        })
        viewModel.trackerNotesMax.observe(viewLifecycleOwner, Observer {
            tv_tracker_max.text = it
        })
        viewModel.trackerNotesAverage.observe(viewLifecycleOwner, Observer {
            tv_tracker_average.text = it
        })
    }
}
