package com.itis.group11801.fedotova.smartfasting.feature_tracker.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.app.di.scope.ScreenScope
import com.itis.group11801.fedotova.smartfasting.feature_diets.DietRouter
import com.itis.group11801.fedotova.smartfasting.feature_news.domain.model.News
import javax.inject.Inject

@ScreenScope
class TrackerViewModel @Inject constructor(
    val router: DietRouter
) : ViewModel() {
    val news = MutableLiveData<List<News>>()

    fun openDietPlan() {
        return router.openDietPlansFragment()
    }
}
