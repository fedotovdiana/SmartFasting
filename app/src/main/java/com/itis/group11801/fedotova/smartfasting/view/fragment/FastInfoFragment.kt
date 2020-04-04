package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.di.Injectable
import com.itis.group11801.fedotova.smartfasting.di.injectViewModel
import com.itis.group11801.fedotova.smartfasting.viewmodel.FastInfoViewModel
import kotlinx.android.synthetic.main.fragment_fasting_graph.*
import javax.inject.Inject

class FastInfoFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FastInfoViewModel

    lateinit var pref: SharedPreferences
    val APP_PREFERENCES = "pref"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO rename layout
        val root = inflater.inflate(R.layout.fragment_fasting_graph, container, false)
        viewModel = injectViewModel(viewModelFactory)

        viewModel.fast.observe(viewLifecycleOwner, Observer {
            tv.text = it.id.toString()
            btn_choose.background = ContextCompat.getDrawable(context!!, it.gradient)
        })
        viewModel.setFast(arguments?.getInt("fastId") ?: 0)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_choose.setOnClickListener {
            val sharedPref = activity!!.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("fastId", viewModel.fast.value?.id.toString())
                apply()
            }
        }
    }
}
