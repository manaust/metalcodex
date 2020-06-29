package be.satanica.metalcodex.ui.fragments.song

import androidx.recyclerview.widget.DiffUtil
import be.satanica.metalcodex.data.models.Song

class SongsDiffCallback(
    private val oldSongs: List<Song>,
    private val newSongs: List<Song>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldSongs.size
    }

    override fun getNewListSize(): Int {
        return newSongs.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldSongs[oldItemPosition].id == newSongs[newItemPosition].id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldSongs[oldItemPosition] == newSongs[newItemPosition]
    }
}
