package be.satanica.metalcodex.ui.fragments.song

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import be.satanica.metalcodex.MobileNavigationDirections
import be.satanica.metalcodex.R
import be.satanica.metalcodex.data.models.Song
import be.satanica.metalcodex.ui.fragments.base.BaseDiffAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.list_item_song.view.*

class SongAdapter : BaseDiffAdapter<Song, SongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun getDiffCallback(oldItems: List<Song>, newItems: List<Song>): DiffUtil.Callback =
        SongsDiffCallback(oldItems, newItems)

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tvSong: TextView = view.tv_song
        private val tvArtist: TextView = view.tv_artist
        private val ivThumbnail: ImageView = view.iv_thumbnail

        fun bind(song: Song) {
            tvSong.text = song.name
            tvArtist.text = song.artist

            Glide.with(itemView.context)
                .asBitmap()
                .load(song.thumbnailSmall)
                .transform(CenterCrop(), RoundedCorners(16))
                .into(ivThumbnail)

            view.setOnClickListener { v ->
                v.findNavController().navigate(
                    MobileNavigationDirections.songDetail(
                        song.id,
                        song.name,
                        song.artist,
                        song.album,
                        song.thumbnailLarge,
                        song.releaseDate,
                        song.bookmarked
                    )
                )
            }
        }
    }
}
