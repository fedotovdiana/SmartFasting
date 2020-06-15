package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_choose_drink.*

class ChooseDialogFragment : BaseDialogFragment<DrinkTrackerViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_choose_drink, container, false)
    }

    override fun inject() {
        AppInjector.injectChooseDialogFragment(this)
    }

    override fun initViews() {
        viewModel.changeState()
        btn_save_drink.setOnClickListener {
            viewModel.saveDrink(spinner.selectedItem.toString(), et_volume.text.toString())
            dismiss()
        }
        tv_cancel_drink.setOnClickListener {
            dismiss()
        }
    }

    override fun subscribe(viewModel: DrinkTrackerViewModel) {
        observe(viewModel.isIncorrect, Observer {
            if (it) {
                et_volume.text.clear()
                Toast.makeText(requireContext(), TOAST_INCORRECT, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        const val TOAST_INCORRECT = "ONLY BETWEEN 50 AND 2000 ML"
    }
}
