package com.itis.group11801.fedotova.smartfasting

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.itis.group11801.fedotova.smartfasting.models.Article
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*


class NewsViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(article: Article, onClick: (String) -> Unit) {

        setImg(article)

        article.apply {
            tv_title.text = title
            tv_description.text = description
            tv_source.text = source?.name
            publishedAt?.let {
                val time = dateFormat(publishedAt)
                tv_time.text = time
            }
        }

        itemView.setOnClickListener {
            article.url?.let {
                onClick(it)
            }
        }
    }

    private fun setImg(article: Article) {
        val requestOptions =
            RequestOptions()
                .placeholder(getRandomDrawableColor())
                .error(getRandomDrawableColor())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()

        Glide.with(containerView)
            .load(article.urlToImage)
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
