package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.fragment_diet_plans.*
import javax.inject.Inject

class DietPlansFragment : Fragment() {

    @Inject
    lateinit var viewModel: DietPlansViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initDietPlansComponent()
        AppInjector.injectDietPlansFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diet_plans, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dietPlans.observe(viewLifecycleOwner, Observer { result ->
            if (rv_fasts.adapter == null) {
                rv_fasts.adapter =
                    DietPlansAdapter {
                        viewModel.showDietPlan(it.id)
                    }
            }
            (rv_fasts.adapter as DietPlansAdapter).submitList(result)
        })
        viewModel.getDietPlans()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearDietPlansComponent()
    }
}
