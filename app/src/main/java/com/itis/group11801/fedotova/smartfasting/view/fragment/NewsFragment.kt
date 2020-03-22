package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.data.Result.Status.*
import com.itis.group11801.fedotova.smartfasting.di.Injectable
import com.itis.group11801.fedotova.smartfasting.di.injectViewModel
import com.itis.group11801.fedotova.smartfasting.utils.hide
import com.itis.group11801.fedotova.smartfasting.utils.intentOpenWebsite
import com.itis.group11801.fedotova.smartfasting.utils.show
import com.itis.group11801.fedotova.smartfasting.view.recycler.news.NewsAdapter
import com.itis.group11801.fedotova.smartfasting.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import javax.inject.Inject

class NewsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        viewModel = injectViewModel(viewModelFactory)
        observeViewModel(root)
        return root
    }

    private fun observeViewModel(root: View) {
        viewModel.news.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                SUCCESS -> {
                    progressBar.hide()
                    result.data?.let { list ->
                        if (rv_news.adapter == null) {
                            root.rv_news.adapter = NewsAdapter { intentOpenWebsite(this, it) }
                        }
                        (rv_news.adapter as NewsAdapter).submitList(list)
                    }
                }
                LOADING -> progressBar.show()
                ERROR -> {
                    progressBar.hide()
                    Snackbar.make(root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}
