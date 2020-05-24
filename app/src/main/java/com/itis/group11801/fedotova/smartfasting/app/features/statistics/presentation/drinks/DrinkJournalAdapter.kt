package com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.itis.group11801.fedotova.smartfasting.R
import com.itis.group11801.fedotova.smartfasting.app.features.statistics.presentation.drinks.model.JournalParent
import kotlinx.android.synthetic.main.item_journal_child.view.*
import kotlinx.android.synthetic.main.item_journal_parent.view.*

class DrinkJournalAdapter(private val items: List<JournalParent>) :
    RecyclerView.Adapter<DrinkJournalAdapter.JournalViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): DrinkJournalAdapter.JournalViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_journal_parent, parent, false)
        return JournalViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DrinkJournalAdapter.JournalViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var context: Context? = null

        override fun onClick(view: View?) {
            if (view?.id == R.id.btn_drop_down ||
                view?.id == R.id.btn_drop_up ||
                view?.id == R.id.ll_parent_content
            ) {
                if (itemView.ll_child_items?.visibility == View.VISIBLE) {
                    itemView.ll_child_items?.visibility = View.GONE
                    itemView.btn_drop_up?.visibility = View.GONE
                    itemView.btn_drop_down?.visibility = View.VISIBLE
                } else {
                    itemView.ll_child_items?.visibility = View.VISIBLE
                    itemView.btn_drop_up?.visibility = View.VISIBLE
                    itemView.btn_drop_down?.visibility = View.GONE
                }
            } else {
                val clicked = view as LinearLayout
                Toast.makeText(context, clicked.tv_sort.text, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        fun bind(item: JournalParent) {
            context = itemView.context
            itemView.tv_date.text = item.date
            itemView.tv_total_volume.text = item.totalVolume.toString()
            itemView.ll_parent_content.setOnClickListener(this)
            itemView.btn_drop_up.setOnClickListener(this)
            itemView.btn_drop_down.setOnClickListener(this)
            itemView.ll_child_items?.visibility = View.GONE

            val size = item.list.size

            for (indexView in 0 until size) {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_journal_child, itemView.ll_child_items, false)
                itemView.ll_child_items.addView(view)
                view.setOnClickListener(this)
            }

            val noOfChildTextViews = (itemView.ll_child_items.childCount)

            if (size < noOfChildTextViews) {
                for (index in size until noOfChildTextViews) {
                    val currentLayout = itemView.ll_child_items.getChildAt(index) as LinearLayout
                    currentLayout.visibility = View.GONE
                }
            }
            for (textViewIndex in 0 until size) {
                val currentLL = itemView.ll_child_items.getChildAt(textViewIndex) as LinearLayout
                val date = currentLL.getChildAt(0) as TextView
                date.text = item.list[textViewIndex].sort
                val totalVolume = currentLL.getChildAt(1) as TextView
                totalVolume.text = item.list[textViewIndex].volume.toString()
            }
        }
    }
}
