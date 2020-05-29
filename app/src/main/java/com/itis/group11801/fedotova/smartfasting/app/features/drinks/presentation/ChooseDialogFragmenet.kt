package com.itis.group11801.fedotova.smartfasting.app.features.drinks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        btn_save_drink.setOnClickListener {
            viewModel.saveDrink(spinner.selectedItem.toString(), et_volume.text.toString())
            dismiss()
        }
        tv_cancel_drink.setOnClickListener {
            dismiss()
        }
    }
}
