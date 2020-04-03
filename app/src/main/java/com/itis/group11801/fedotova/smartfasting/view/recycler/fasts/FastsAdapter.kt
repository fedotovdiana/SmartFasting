package com.itis.group11801.fedotova.smartfasting.view.recycler.fasts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itis.group11801.fedotova.smartfasting.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_fast.*

class FastsAdapter(
//    private val data: List<Fast>,
    private val onClick: (Fast) -> Unit
) : ListAdapter<Fast, FastsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FastsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_fast,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FastsViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

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

object DiffCallback : DiffUtil.ItemCallback<Fast>() {
    override fun areItemsTheSame(oldItem: Fast, newItem: Fast): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Fast, newItem: Fast): Boolean {
        return oldItem == newItem
    }
}

data class Fast(
    val title: String,
    val desc: String,
    val color: Int
)
