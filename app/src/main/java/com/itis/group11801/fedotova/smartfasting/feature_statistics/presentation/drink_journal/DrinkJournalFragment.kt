package com.itis.group11801.fedotova.smartfasting.feature_statistics.presentation.drink_journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.fragment_drink_journal.*
import javax.inject.Inject

class DrinkJournalFragment : Fragment() {

    @Inject
    lateinit var viewModel: DrinkJournalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initDrinkJournalComponent()
        AppInjector.injectDrinkJournalFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drink_journal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.drinkNotes.observe(viewLifecycleOwner, Observer {
            rv_journal.adapter = DrinkJournalAdapter(it)
            rv_journal.setHasFixedSize(true)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearNewsComponent()
    }
}
