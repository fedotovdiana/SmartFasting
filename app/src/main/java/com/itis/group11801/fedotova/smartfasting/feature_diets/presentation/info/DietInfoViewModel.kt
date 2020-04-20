package com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import com.itis.group11801.fedotova.smartfasting.feature_diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.feature_diets.domain.DietInteractor
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.mapper.mapDietToDietInfoUI
import com.itis.group11801.fedotova.smartfasting.feature_diets.presentation.info.model.DietInfoUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ScreenScope
class DietInfoViewModel @Inject constructor(
    private val router: DietRouter,
    private val interactor: DietInteractor,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private lateinit var job: Job

    private var _diet = MutableLiveData<DietInfoUI>()
    var diet: LiveData<DietInfoUI> = _diet

    fun setDietPlan(id: Int) {
        job = viewModelScope.launch {
            _diet.value = mapDietToDietInfoUI(
                resourceManager,
                withContext(Dispatchers.IO) { interactor.getDietPlan(id) })
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun getDefaultColor(): Int {
        return resourceManager.getColor(R.color.colorPrimary)
    }
}
