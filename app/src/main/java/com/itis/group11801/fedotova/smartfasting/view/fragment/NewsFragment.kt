package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.di.Injectable
import com.itis.group11801.fedotova.smartfasting.di.injectViewModel
import com.itis.group11801.fedotova.smartfasting.view.recycler.news.NewsAdapter
import com.itis.group11801.fedotova.smartfasting.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initNewsComponent()
        AppInjector.injectNewsFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        viewModel = injectViewModel(viewModelFactory)
        setHasOptionsMenu(true)
        observeViewModel()
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateDb()
    }

    private fun observeViewModel() {
        viewModel.news.observe(viewLifecycleOwner, Observer { result ->
            if (rvNews.adapter == null) {
                rvNews.adapter = NewsAdapter { viewModel.newsClicked(it) }
            }
            (rvNews.adapter as NewsAdapter).submitList(result)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearNewsComponent()
    }
}
