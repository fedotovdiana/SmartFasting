package com.itis.group11801.fedotova.smartfasting.view.fragment

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
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.FastsAdapter
import com.itis.group11801.fedotova.smartfasting.viewmodel.DietPlansViewModel
import kotlinx.android.synthetic.main.fragment_diet_plans.*
import javax.inject.Inject

class DietPlansFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DietPlansViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_diet_plans, container, false)
        viewModel = injectViewModel(viewModelFactory)
        observeViewModel()
        return root
    }

    private fun observeViewModel() {
        viewModel.dietPlans.observe(viewLifecycleOwner, Observer { result ->
            if (rv_fasts.adapter == null) {
                rv_fasts.adapter = FastsAdapter { viewModel.showDietPlan(it.id) }
            }
            (rv_fasts.adapter as FastsAdapter).submitList(result)
        })
        viewModel.getDietPlans()
    }
}
