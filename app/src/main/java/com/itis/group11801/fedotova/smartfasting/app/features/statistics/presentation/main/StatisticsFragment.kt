package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.fragment_statistics.*
import javax.inject.Inject

class StatisticsFragment : Fragment(), OnChartValueSelectedListener {

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
        observeViewModel()
        setupBarChart()
    }

    private fun observeViewModel() {
        viewModel.trackerNotes.observe(viewLifecycleOwner, Observer {
            Log.e("NOTE", it)
        })
        viewModel.drinkVolumeTotal.observe(viewLifecycleOwner, Observer {
            tv_stat_total_volume.text = it
        })
        viewModel.drinkVolumeAverage.observe(viewLifecycleOwner, Observer {
            tv_stat_pop_drink.text = it
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
        viewModel.labels.observe(viewLifecycleOwner, Observer {
            with(chart_tracker.xAxis) {
                valueFormatter = IndexAxisValueFormatter(it)
                setLabelCount(it.size, true)
            }
        })
        viewModel.data.observe(viewLifecycleOwner, Observer {
            chart_tracker.data = it
        })
    }

    private fun setupBarChart() {
        with(chart_tracker) {
            setOnChartValueSelectedListener(this@StatisticsFragment)
            description.isEnabled = false
            legend.isEnabled = false
            setDrawBarShadow(true)
            moveViewToX(100f)
            animateY(500)
            setDrawGridBackground(false)
            setVisibleXRangeMaximum(8f)
            setNoDataText("")
            setDrawBarShadow(false)
            with(xAxis) {
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 10f
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setCenterAxisLabels(true)
                textColor = viewModel.getTextColor()
            }
            with(axisRight) {
                spaceTop = 0f
                spaceBottom = 0f
                axisMinimum = 0f
                textColor = viewModel.getTextColor()
                setDrawAxisLine(false)
                setDrawGridLines(true)
            }
            with(axisLeft) {
                isEnabled = false
                axisMinimum = 0f
                spaceTop = 0f
            }
        }
    }

    override fun onNothingSelected() {
        chart_tracker.data.setDrawValues(false)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        chart_tracker.data.setDrawValues(true)
    }
}
