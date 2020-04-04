package com.itis.group11801.fedotova.smartfasting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.group11801.fedotova.smartfasting.navigation.FastsRouter
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.Fast
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.FastsObject
import kotlinx.coroutines.Job
import javax.inject.Inject

class FastInfoViewModel @Inject constructor(
    val router: FastsRouter
) : ViewModel() {

    private val job = Job()

    private var _fast = MutableLiveData<Fast>()
    var fast: LiveData<Fast> = _fast

    fun setFast(id: Int) {
        _fast.value = FastsObject.fasts[id]
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
