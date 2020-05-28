package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks

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
import com.itis.group11801.fedotova.smartfasting.app.base.BaseFragment
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.fragment_drink_journal.*

class DrinkJournalFragment : BaseFragment<DrinkJournalViewModel>(), OnChartValueSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drink_journal, container, false)
    }

    override fun inject() {
        AppInjector.initDrinkJournalComponent()
        AppInjector.injectDrinkJournalFragment(this)
    }

    override fun initViews() {
        setupBarChart()
    }

    override fun subscribe(viewModel: DrinkJournalViewModel) {
        observe(viewModel.journal, Observer {
            rv_journal.adapter = DrinkJournalAdapter(it)
            rv_journal.setHasFixedSize(true)
        })
        observe(viewModel.labels, Observer { setLabels(it) })
        observe(viewModel.data, Observer { setData(it) })
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearDrinkJournalComponent()
    }

    override fun onNothingSelected() {
        barChart.data.setDrawValues(false)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        barChart.data.setDrawValues(true)
    }

    private fun setupBarChart() {
        with(barChart) {
            setOnChartValueSelectedListener(this@DrinkJournalFragment)
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
        with(barChart) {
            data = barData
            moveViewToX(100f)
            setVisibleXRangeMaximum(6f)
        }
    }

    private fun setLabels(labels: MutableList<String>) {
        with(barChart.xAxis) {
            if (labels.size < 2) isEnabled = false
            else {
                valueFormatter = IndexAxisValueFormatter(labels)
                labelCount = labels.size
            }
        }
    }
}
