package com.itis.group11801.fedotova.smartfasting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.group11801.fedotova.smartfasting.models.Article


class NewsAdapter(
    private val data: List<Article>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position], onClick)
    }
}
