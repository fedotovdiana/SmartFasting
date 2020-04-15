package com.itis.group11801.fedotova.smartfasting.feature_drink.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.dialog_fragment_choose_drink.*
import javax.inject.Inject

class ChooseDialogFragment : DialogFragment() {

    @Inject
    lateinit var viewModel: ChooseDialogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
//        AppInjector.initDrinkComponent()
        AppInjector.injectChooseDialogFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_choose_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_save_drink.setOnClickListener {
            viewModel.saveDrink(
                spinner.selectedItem.toString(),
                et_volume.text.toString()
            )
            viewModel.router.closeDrinkDialog()
        }
        tv_cancel_drink.setOnClickListener {
            viewModel.router.closeDrinkDialog()
        }
    }
}
