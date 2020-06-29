package be.satanica.metalcodex.ui.fragments.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDiffAdapter<T, VH : RecyclerView.ViewHolder> : BaseAdapter<T, VH>() {

    override fun updateItems(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(getDiffCallback(items, newItems))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    abstract fun getDiffCallback(oldItems: List<T>, newItems: List<T>): DiffUtil.Callback
}
