package com.itis.group11801.fedotova.smartfasting.app.features.tracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.dialog_fragment_confirm_stop.*
import javax.inject.Inject

class ConfirmStopDialogFragment : DialogFragment() {

    @Inject
    lateinit var viewModel: TrackerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.injectConfirmStopDialogFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_confirm_stop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_stop_timer.setOnClickListener {
            viewModel.stopTimer()
            dismiss()
        }
        tv_cancel_timer.setOnClickListener {
            dismiss()
        }
    }
}
