package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.itis.group11801.fedotova.smartfasting.data.ResultWrapper.Status.*
import com.itis.group11801.fedotova.smartfasting.databinding.FragmentNewsBinding
import com.itis.group11801.fedotova.smartfasting.di.Injectable
import com.itis.group11801.fedotova.smartfasting.di.injectViewModel
import com.itis.group11801.fedotova.smartfasting.utils.hide
import com.itis.group11801.fedotova.smartfasting.utils.show
import com.itis.group11801.fedotova.smartfasting.view.recycler.news.NewsAdapter
import com.itis.group11801.fedotova.smartfasting.viewmodel.NewsViewModel
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
        viewModel = injectViewModel(viewModelFactory)

        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = NewsAdapter { viewModel.router.intentOpenWebsite(this, it) }
        binding.rvNews.adapter = adapter

        observeViewModel(binding, adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun observeViewModel(binding: FragmentNewsBinding, adapter: NewsAdapter) {
        viewModel.getNews()
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { adapter.submitList(it) }
                }
                LOADING -> binding.progressBar.show()
                ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}
