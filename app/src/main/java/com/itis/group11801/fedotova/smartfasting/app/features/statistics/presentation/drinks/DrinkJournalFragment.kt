package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_drink_journal.*
import javax.inject.Inject

class DrinkJournalFragment : Fragment(), OnChartValueSelectedListener {

    @Inject
    lateinit var viewModel: DrinkJournalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initDrinkJournalComponent()
        AppInjector.injectDrinkJournalFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drink_journal, container, false)
        observeViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBarChart()
    }

    private fun observeViewModel() {
        viewModel.journal.observe(viewLifecycleOwner, Observer {
            rv_journal.adapter = DrinkJournalAdapter(it)
            rv_journal.setHasFixedSize(true)
        })
        viewModel.labels.observe(viewLifecycleOwner, Observer {
            with(barChart.xAxis) {
                valueFormatter = IndexAxisValueFormatter(it)
                labelCount = it.size
            }
        })
        viewModel.data.observe(viewLifecycleOwner, Observer {
            barChart.data = it
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearDrinkJournalComponent()
    }

    private fun setupBarChart() {
        with(barChart) {
            setOnChartValueSelectedListener(this@DrinkJournalFragment)
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
        barChart.data.setDrawValues(false)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        barChart.data.setDrawValues(true)
    }
}
