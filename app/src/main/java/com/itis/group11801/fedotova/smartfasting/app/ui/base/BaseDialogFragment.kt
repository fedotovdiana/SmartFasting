package com.itis.group11801.fedotova.smartfasting.app.ui.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseDialogFragment<T : ViewModel> : DialogFragment() {

    @Inject
    protected open lateinit var viewModel: T

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
        initViews()
    }

    abstract fun inject()

    abstract fun initViews()
}