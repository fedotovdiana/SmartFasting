package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietInteractorImpl
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.mapper.mapDietToDietPlanUI
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.model.DietPlanUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class DietPlansViewModel @Inject constructor(
    private val router: DietRouter,
    private val interactor: DietInteractorImpl,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private lateinit var job: Job

    private var _dietPlans = MutableLiveData<List<DietPlanUI>>()
    val dietPlans: LiveData<List<DietPlanUI>>
        get() = _dietPlans

    fun getDietPlans() {
        job = viewModelScope.launch(Dispatchers.IO) {
            val dietPlans =
                interactor.getDietPlans().map {
                    mapDietToDietPlanUI(resourceManager, it)
                }
            _dietPlans.postValue(dietPlans)
        }
    }

    fun showDietPlan(id: Int) {
        val bundle = bundleOf("dietPlanId" to id)
        router.openDietInfoFragment(bundle)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
