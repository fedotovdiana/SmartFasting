package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.di.Injectable
import com.itis.group11801.fedotova.smartfasting.di.injectViewModel
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.Fast
import com.itis.group11801.fedotova.smartfasting.view.recycler.fasts.FastsAdapter
import com.itis.group11801.fedotova.smartfasting.viewmodel.TrackerViewModel
import kotlinx.android.synthetic.main.fragment_fast_tracker.*
import javax.inject.Inject

class TrackerFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TrackerViewModel
    private lateinit var adapter: FastsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fast_tracker, container, false)
        viewModel = injectViewModel(viewModelFactory)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FastsAdapter {
            showDetails(it)
        }
        adapter.submitList(fasts)
        rv_fasts.adapter = adapter
    }

    private fun showDetails(fast: Fast) {
        Toast.makeText(activity, fast.title, Toast.LENGTH_LONG).show()
    }

    private val fasts = listOf(
        Fast(
            "20:4 intermittent",
            "quantifiesSDFGHGFDkkmkmkmk",
            R.drawable.custom_btn_purple
        ),
        Fast(
            "16:8 intermittent",
            "qwerthyjuiuytfrdesaSDFGHGFD",
            R.drawable.custom_btn_green
        ),
        Fast(
            "12:12 intermittent",
            "qwerthyjuiuytfrdesaSDFGHGFD",
            R.drawable.custom_btn_yellow
        ),
        Fast(
            "10:14 intermittent",
            "qwerthyjuiuytfrdesaSDFGHGFD",
            R.drawable.custom_btn_blue
        )
    )
}
