package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_diet_plans.*

class DietPlansFragment : BaseFragment<DietPlansViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diet_plans, container, false)
    }

    override fun inject() {
        AppInjector.initDietPlansComponent()
        AppInjector.injectDietPlansFragment(this)
    }

    override fun initViews() {}

    override fun subscribe(viewModel: DietPlansViewModel) {
        observe(viewModel.dietPlans, Observer { result ->
            if (rv_fasts.adapter == null) {
                rv_fasts.adapter = DietPlansAdapter {
                    viewModel.showDietPlan(createBundle(it.id))
                }
            }
            (rv_fasts.adapter as DietPlansAdapter).submitList(result)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearDietPlansComponent()
    }

    companion object {
        private const val DIET_PLAN_ID = "smartfasting.diet_plan"

        fun createBundle(dietId: Int): Bundle {
            return bundleOf(DIET_PLAN_ID to dietId)
        }
    }
}
