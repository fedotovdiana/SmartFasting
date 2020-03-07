package com.itis.group11801.fedotova.smartfasting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FastsAdapter(
    private val data: List<Fast>,
    private val onClick: (Fast) -> Unit
) : RecyclerView.Adapter<FastsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FastsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_fast,
                parent,
                false
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FastsViewHolder, position: Int) {
        holder.bind(data[position], onClick)
    }
}
