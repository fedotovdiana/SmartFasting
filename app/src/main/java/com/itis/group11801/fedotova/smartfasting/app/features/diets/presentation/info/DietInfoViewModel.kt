package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.app.features.diets.domain.DietInteractor
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.mapper.mapDietToDietInfoUI
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.info.model.DietInfoUI
import com.itis.group11801.fedotova.smartfasting.app.resources.ResourceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class DietInfoViewModel @Inject constructor(
    private val interactor: DietInteractor,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private var _diet = MutableLiveData<DietInfoUI>()
    var diet: LiveData<DietInfoUI> = _diet

    fun setDiet(id: Int) {
        viewModelScope.launch {
            _diet.value = mapDietToDietInfoUI(resourceManager, interactor.getDiet(id))
        }
    }

    fun chooseDietPlan() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.setDietID(diet.value?.id ?: 0)
        }
    }

    fun getDefaultColor(): Int {
        return resourceManager.getColor(R.color.colorPrimary)
    }
}
