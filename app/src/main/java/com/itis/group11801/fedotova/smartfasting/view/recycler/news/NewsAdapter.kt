package com.itis.group11801.fedotova.smartfasting.view.recycler.news

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.data.local.News
import com.itis.group11801.fedotova.smartfasting.utils.dateFormat
import com.itis.group11801.fedotova.smartfasting.utils.getRandomDrawableColor
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*


class NewsAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<News, NewsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

class NewsViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(news: News, onClick: (String) -> Unit) {

        setImg(news)

        news.apply {
            tv_title.text = title
            tv_description.text = description
            tv_source.text = source?.name
            publishedAt?.let {
                val time =
                    dateFormat(
                        publishedAt
                    )
                tv_time.text = time
            }
        }

        itemView.setOnClickListener {
            news.url?.let {
                onClick(it)
            }
        }
    }

    private fun setImg(news: News) {
        val requestOptions =
            RequestOptions()
                .placeholder(getRandomDrawableColor())
                .error(getRandomDrawableColor())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()

        Glide.with(containerView)
            .load(news.urlToImage)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    @Nullable e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress_load_photo.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress_load_photo.visibility = View.GONE
                    return false
                }
            })
            .into(img_news)
    }
}

object DiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}
