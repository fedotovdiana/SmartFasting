package com.itis.group11801.fedotova.smartfasting.app.features.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.di.AppInjector
import com.itis.group11801.fedotova.smartfasting.app.ui.base.BaseFragment
import com.itis.group11801.fedotova.smartfasting.app.ui.utils.hide
import com.itis.group11801.fedotova.smartfasting.app.ui.utils.show
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment<NewsViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun inject() {
        AppInjector.initNewsComponent()
        AppInjector.injectNewsFragment(this)
    }

    override fun initViews() {}

    override fun subscribe(viewModel: NewsViewModel) {
        observe(viewModel.news, Observer { result ->
            if (result.isEmpty()) {
                iv_no_internet.show()
            } else {
                iv_no_internet.hide()
                if (rvNews.adapter == null) {
                    rvNews.adapter = NewsAdapter { viewModel.newsClicked(it) }
                }
                (rvNews.adapter as NewsAdapter).submitList(result)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.update()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearNewsComponent()
    }
}
