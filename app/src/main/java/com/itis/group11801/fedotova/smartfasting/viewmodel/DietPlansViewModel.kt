package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.domain.interactor.DietPlansInteractor
import com.itis.group11801.fedotova.smartfasting.navigation.DietPlansRouter
import com.itis.group11801.fedotova.smartfasting.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.view.mapper.mapDietPlanToDietPlanUI
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.DietPlanUI
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class DietPlansViewModel @Inject constructor(
    val router: DietPlansRouter,
    val interactor: DietPlansInteractor,
    val resourceManager: ResourceManager
) : ViewModel() {

    private lateinit var job: Job

    private var _dietPlans = MutableLiveData<List<DietPlanUI>>()
    val dietPlans: LiveData<List<DietPlanUI>>
        get() = _dietPlans

    fun getDietPlans() {
        job = viewModelScope.launch {
            val dietPlans =
                interactor.getDietPlans().map { mapDietPlanToDietPlanUI(resourceManager, it) }
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
