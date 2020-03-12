package com.itis.group11801.fedotova.smartfasting.view.recycler.fasts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_fast.*

class FastsViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(fast: Fast, onClick: (Fast) -> Unit) {
        tv_fast_title.text = fast.title
        tv_fast_desc.text = fast.desc
        itemView.setOnClickListener { onClick(fast) }
        itemView.setBackgroundResource(fast.color)
    }
}
