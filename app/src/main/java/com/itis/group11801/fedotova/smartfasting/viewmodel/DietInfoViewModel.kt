package com.itis.group11801.fedotova.smartfasting.viewmodel

import android.graphics.drawable.Drawable
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

class DietInfoViewModel @Inject constructor(
    val router: DietPlansRouter,
    val interactor: DietPlansInteractor,
    val resourceManager: ResourceManager
) : ViewModel() {

    private lateinit var job: Job

    private var _diet = MutableLiveData<DietPlanUI>()
    var diet: LiveData<DietPlanUI> = _diet

    fun setDietPlan(id: Int) {
        job = viewModelScope.launch {
            _diet.value = mapDietPlanToDietPlanUI(resourceManager, interactor.getDietPlan(id))
        }
    }

    fun toDrawable(res: Int): Drawable? {
        return resourceManager.getDrawable(res)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
