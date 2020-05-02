package com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.features.diets.presentation.plans.model.DietUI
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_diet.*

class DietPlansAdapter(
    private val onClick: (DietUI) -> Unit
) : ListAdapter<DietUI, DietPlansViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DietPlansViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_diet, parent, false)
        )

    override fun onBindViewHolder(holder: DietPlansViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

class DietPlansViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(dietUI: DietUI, onClick: (DietUI) -> Unit) {
        tv_fast_title.text = dietUI.title
        itemView.setOnClickListener { onClick(dietUI) }
        itemView.setBackgroundResource(dietUI.gradient)
    }
}

object DiffCallback : DiffUtil.ItemCallback<DietUI>() {
    override fun areItemsTheSame(oldItem: DietUI, newItem: DietUI): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DietUI, newItem: DietUI): Boolean {
        return oldItem == newItem
    }
}