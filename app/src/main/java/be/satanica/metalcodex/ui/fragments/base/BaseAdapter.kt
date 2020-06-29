package be.satanica.metalcodex.ui.fragments.base

import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected var items: List<T> = ArrayList()

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): T = items[position]

    open fun updateItems(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }
}
