package com.itis.group11801.fedotova.smartfasting.ui.notifications

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itis.group11801.fedotova.smartfasting.NewsAdapter
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.net.ApiFactory
import com.itis.group11801.fedotova.smartfasting.net.ApiService
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.coroutines.*

class NotificationsFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    lateinit var service: ApiService
    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        service = ApiFactory.apiService

        launch {
            val articles = withContext(Dispatchers.IO) {
                service.getNews().articles
            }
            articles?.let {
                adapter = NewsAdapter(articles) {
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
