package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_confirm_stop.*

class ConfirmStopDialogFragment : BaseDialogFragment<TrackerViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_confirm_stop, container, false)
    }

    override fun inject() {
        AppInjector.injectConfirmStopDialogFragment(this)
    }

    override fun initViews() {
        btn_stop_timer.setOnClickListener {
            viewModel.stopTimer()
            Toast.makeText(context, TIMER_STOPPED, Toast.LENGTH_SHORT).show()
            dismiss()
        }
        tv_cancel_timer.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val TIMER_STOPPED = "TIMER STOPPED"
    }

    override fun subscribe(viewModel: TrackerViewModel) {}
}
