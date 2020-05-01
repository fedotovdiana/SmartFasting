package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietInteractorImpl
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.mapper.mapDietToDietInfoUI
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.model.DietInfoUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.app.utils.tracker.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ScreenScope
class DietInfoViewModel @Inject constructor(
    private val router: DietRouter,
    private val interactor: DietInteractorImpl,
    private val resourceManager: ResourceManager,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private var _diet = MutableLiveData<DietInfoUI>()
    var diet: LiveData<DietInfoUI> = _diet

    fun setDietPlan(id: Int) {
        viewModelScope.launch {
            _diet.value = mapDietToDietInfoUI(
                resourceManager,
                withContext(Dispatchers.IO) { interactor.getDietPlan(id) })
        }
    }

    fun choosePlan() {
        preferenceManager.setDietPlan(diet.value?.id ?: 0)
    }

    fun getDefaultColor(): Int {
        return resourceManager.getColor(R.color.colorPrimary)
    }
}
