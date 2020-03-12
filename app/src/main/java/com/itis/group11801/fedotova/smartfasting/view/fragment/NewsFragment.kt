package com.itis.group11801.fedotova.smartfasting.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.application.App
import com.itis.group11801.fedotova.smartfasting.data.remote.service.NewsApiService
import com.itis.group11801.fedotova.smartfasting.view.recycler.news.NewsAdapter
import com.itis.group11801.fedotova.smartfasting.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.*
import javax.inject.Inject

class NewsFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var service: NewsApiService
    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        App.component.inject(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        launch {
            val articles = withContext(Dispatchers.IO) {
                service.getNews().articles
            }
            articles?.let {
                adapter =
                    NewsAdapter(
                        articles
                    ) {
                        showDetails(it)
                    }
            }
            rv_news.adapter = adapter
        }
    }

    private fun showDetails(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }
}
