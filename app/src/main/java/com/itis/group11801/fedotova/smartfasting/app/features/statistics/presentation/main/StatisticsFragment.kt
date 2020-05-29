package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main

import android.os.Bundle
import android.util.Log
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
import com.itis.group11801.fedotova.smartfasting.app.base.BaseFragment
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
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
            tv_welcome.visibility = View.VISIBLE
            ll_stat.visibility = View.GONE
            ll_graph.visibility = View.GONE
        } else if (!isDrinkAdded) {
            tv_welcome.visibility = View.GONE
            cv_drink.visibility = View.GONE
            tv_see_more.visibility = View.GONE
        } else if (!isTrackerNoteAdded) {
            tv_welcome.visibility = View.GONE
            cv_tracker.visibility = View.GONE
            ll_graph.visibility = View.GONE
        } else {
            tv_welcome.visibility = View.GONE
            ll_stat.visibility = View.VISIBLE
            ll_graph.visibility = View.VISIBLE
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
                Log.e("DDD", labels.size.toString())
                Log.e("DDD", labels.toString())
            }
        }
    }
}
