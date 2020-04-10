package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.di.Injectable
import com.itis.group11801.fedotova.smartfasting.di.injectViewModel
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.Fast
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.FastsAdapter
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.FastsObject
import com.itis.group11801.fedotova.smartfasting.viewmodel.FastsViewModel
import kotlinx.android.synthetic.main.fragment_fasts.*
import javax.inject.Inject

class FastsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FastsViewModel
    private lateinit var adapter: FastsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fasts, container, false)
        viewModel = injectViewModel(viewModelFactory)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FastsAdapter {
            showDetails(it)
        }
        adapter.submitList(FastsObject.fasts)
        rv_fasts.adapter = adapter
    }

    private fun showDetails(fast: Fast) {
        val bundle = bundleOf("fastId" to fast.id)
        viewModel.router.openFastInfoFragment(bundle)
    }
}
