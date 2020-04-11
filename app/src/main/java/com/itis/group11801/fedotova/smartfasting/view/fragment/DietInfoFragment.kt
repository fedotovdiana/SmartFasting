package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.di.Injectable
import com.itis.group11801.fedotova.smartfasting.di.injectViewModel
import com.itis.group11801.fedotova.smartfasting.viewmodel.DietInfoViewModel
import kotlinx.android.synthetic.main.fragment_diet_graph.*
import javax.inject.Inject

class DietInfoFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DietInfoViewModel

    lateinit var pref: SharedPreferences
    val APP_PREFERENCES = "pref"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO rename layout
        viewModel = injectViewModel(viewModelFactory)
        observeViewModel()
        return inflater.inflate(R.layout.fragment_diet_graph, container, false)
    }

    private fun observeViewModel() {
        viewModel.diet.observe(viewLifecycleOwner, Observer {
            tv_fast_info.text = it.desc
            btn_choose.background = viewModel.toDrawable(it.gradient)
        })
        viewModel.setDietPlan(arguments?.getInt("dietPlanId") ?: 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_choose.setOnClickListener {
            val sharedPref = activity!!.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("dietPlanId", viewModel.diet.value?.id.toString())
                apply()
            }
        }
    }
}
