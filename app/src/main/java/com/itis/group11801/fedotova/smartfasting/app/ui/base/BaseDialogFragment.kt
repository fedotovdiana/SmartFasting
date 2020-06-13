package com.itis.group11801.fedotova.smartfasting.app.ui.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseDialogFragment<T : ViewModel> : DialogFragment() {

    @Inject
    protected open lateinit var viewModel: T

    private val observables = mutableListOf<LiveData<*>>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
        initViews()
        subscribe(viewModel)
    }

    override fun onDestroyView() {
        observables.forEach { it.removeObservers(this) }
        super.onDestroyView()
    }

    @Suppress("unchecked_cast")
    protected fun <V : Any?> observe(source: LiveData<V>, observer: Observer<V>) {
        source.observe(this, observer as Observer<in Any?>)
        observables.add(source)
    }

    abstract fun inject()

    abstract fun initViews()

    abstract fun subscribe(viewModel: T)
}