package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.ui.base.BaseFragment
import com.itis.group11801.fedotova.smartfasting.app.ui.utils.hide
import com.itis.group11801.fedotova.smartfasting.app.ui.utils.show
import kotlinx.android.synthetic.main.fragment_statistics.*

class StatisticsFragment : BaseFragment<StatisticsViewModel>(), OnChartValueSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun inject() {
        AppInjector.initStatisticsComponent()
        AppInjector.injectStatisticsFragment(this)
    }

    override fun initViews() {
        val isDrinkAdded = viewModel.checkDrinkAdded()
        val isTrackerNoteAdded = viewModel.checkTrackerNoteAdded()
        if (!(isDrinkAdded || isTrackerNoteAdded)) {
            tv_welcome.show()
            ll_stat.hide()
            ll_graph.hide()
        } else if (!isDrinkAdded) {
            tv_welcome.hide()
            cv_drink.hide()
            tv_see_more.hide()
        } else if (!isTrackerNoteAdded) {
            tv_welcome.hide()
            cv_tracker.hide()
            ll_graph.hide()
        } else {
            tv_welcome.hide()
            ll_stat.show()
            ll_graph.show()
        }
        tv_see_more.setOnClickListener {
            viewModel.openDrinkJournal()
        }
        setupBarChart()
    }

    override fun subscribe(viewModel: StatisticsViewModel) {
        observe(viewModel.drinkVolumeTotal, Observer {
            tv_stat_total_volume.text = it
        })
        observe(viewModel.drinkVolumeAverage, Observer {
            tv_stat_pop_drink.text = it
        })
        observe(viewModel.trackerNotesCount, Observer {
            tv_tracker_count.text = it
        })
        observe(viewModel.trackerNotesMin, Observer {
            tv_tracker_min.text = it
        })
        observe(viewModel.trackerNotesMax, Observer {
            tv_tracker_max.text = it
        })
        observe(viewModel.trackerNotesAverage, Observer {
            tv_tracker_average.text = it
        })
        observe(viewModel.labels, Observer {
            setLabels(it)
        })
        observe(viewModel.data, Observer {
            setData(it)
        })
    }

    override fun onDestroy() {
        AppInjector.clearStatisticsComponent()
        super.onDestroy()
    }

    override fun onNothingSelected() {
        chart_tracker.data.setDrawValues(false)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        chart_tracker.data.setDrawValues(true)
    }

    private fun setupBarChart() {
        with(chart_tracker) {
            setOnChartValueSelectedListener(this@StatisticsFragment)
            description.isEnabled = false
            legend.isEnabled = false
            setDrawBarShadow(true)
            animateY(500)
            setDrawGridBackground(false)
            setNoDataText("")
            setDrawBarShadow(false)
            with(xAxis) {
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 10f
                setDrawAxisLine(false)
                setDrawGridLines(false)
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

    private fun setData(barData: BarData?) {
        with(chart_tracker) {
            data = barData
            moveViewToX(100f)
            setVisibleXRangeMaximum(6f)
        }
    }

    private fun setLabels(labels: MutableList<String>) {
        with(chart_tracker.xAxis) {
            if (labels.size < 2) isEnabled = false
            else {
                valueFormatter = IndexAxisValueFormatter(labels)
                labelCount = labels.size
            }
        }
    }
}
