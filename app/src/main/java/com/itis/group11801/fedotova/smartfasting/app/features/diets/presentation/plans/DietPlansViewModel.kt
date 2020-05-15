package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.mapper.mapDietToDietUI
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.model.DietUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class DietPlansViewModel @Inject constructor(
    private val router: DietRouter,
    private val interactor: DietInteractor,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private var _dietPlans = MutableLiveData<List<DietUI>>()
    val dietPlans: LiveData<List<DietUI>>
        get() = _dietPlans

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dietPlans =
                interactor.getDiets().map { mapDietToDietUI(resourceManager, it) }
            _dietPlans.postValue(dietPlans)
        }
    }

    fun showDietPlan(bundle: Bundle) {
        router.openDietInfoFragment(bundle)
    }
}
