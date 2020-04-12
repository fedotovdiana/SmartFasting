package com.itis.group11801.fedotova.smartfasting.view.recycler.fasts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itis.group11801.fedotova.smartfasting.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_diet.*

class DietPlansAdapter(
    private val onClick: (DietPlanUI) -> Unit
) : ListAdapter<DietPlanUI, DietPlansViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DietPlansViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_diet,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DietPlansViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

class DietPlansViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(dietPlanUI: DietPlanUI, onClick: (DietPlanUI) -> Unit) {
        tv_fast_title.text = dietPlanUI.title
        itemView.setOnClickListener { onClick(dietPlanUI) }
        itemView.setBackgroundResource(dietPlanUI.gradient)
    }
}

object DiffCallback : DiffUtil.ItemCallback<DietPlanUI>() {
    override fun areItemsTheSame(oldItem: DietPlanUI, newItem: DietPlanUI): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DietPlanUI, newItem: DietPlanUI): Boolean {
        return oldItem == newItem
    }
}