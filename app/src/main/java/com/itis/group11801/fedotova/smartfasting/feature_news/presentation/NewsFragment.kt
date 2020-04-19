package com.itis.group11801.fedotova.smartfasting.feature_news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModel: NewsViewModel

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
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateDb()
    }

    private fun observeViewModel() {
        viewModel.news.observe(viewLifecycleOwner, Observer { result ->
            if (rvNews.adapter == null) {
                rvNews.adapter =
                    NewsAdapter {
                        viewModel.newsClicked(it)
                    }
            }
            (rvNews.adapter as NewsAdapter).submitList(result)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearNewsComponent()
    }
}
