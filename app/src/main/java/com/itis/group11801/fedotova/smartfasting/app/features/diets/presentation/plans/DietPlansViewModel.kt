package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.mapper.mapDietToDietPlanUI
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.model.DietPlanUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.DIET_PLAN_ID
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class DietPlansViewModel @Inject constructor(
    private val router: DietRouter,
    private val interactor: DietInteractor,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private var _dietPlans = MutableLiveData<List<DietPlanUI>>()
    val dietPlans: LiveData<List<DietPlanUI>>
        get() = _dietPlans

    fun getDietPlans() {
        viewModelScope.launch {
            val dietPlans =
                interactor.getDiets().map { mapDietToDietPlanUI(resourceManager, it) }
            _dietPlans.postValue(dietPlans)
        }
    }

    fun showDietPlan(id: Int) {
        val bundle = bundleOf(DIET_PLAN_ID to id)
        router.openDietInfoFragment(bundle)
    }
}
