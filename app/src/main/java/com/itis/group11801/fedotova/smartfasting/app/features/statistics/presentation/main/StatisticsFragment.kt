package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.itis.group11801.fedotova.smartfasting.R
import kotlinx.android.synthetic.main.fragment_statistics.*

class StatisticsFragment : Fragment() {

    companion object {
        fun newInstance() =
            StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_dj.setOnClickListener {
            findNavController().navigate(R.id.navigation_drink_journal)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatisticsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
