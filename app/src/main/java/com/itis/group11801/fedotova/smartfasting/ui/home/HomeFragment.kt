package com.itis.group11801.fedotova.smartfasting.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itis.group11801.fedotova.smartfasting.Fast
import com.itis.group11801.fedotova.smartfasting.FastsAdapter
import com.itis.group11801.fedotova.smartfasting.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var adapter: FastsAdapter

    private val fasts = listOf(
        Fast("20:4 intermittent", "quantifiesSDFGHGFDkkmkmkmk", R.drawable.custom_btn_purple),
        Fast("16:8 intermittent", "qwerthyjuiuytfrdesaSDFGHGFD", R.drawable.custom_btn_green),
        Fast("12:12 intermittent", "qwerthyjuiuytfrdesaSDFGHGFD", R.drawable.custom_btn_yellow),
        Fast("10:14 intermittent", "qwerthyjuiuytfrdesaSDFGHGFD", R.drawable.custom_btn_blue)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.textView)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FastsAdapter(fasts) {
            showDetails(it)
        }
        rv_fasts.adapter = adapter
    }

    private fun showDetails(fast: Fast) {
        Toast.makeText(activity, fast.title.toString(), Toast.LENGTH_LONG).show()
    }
}