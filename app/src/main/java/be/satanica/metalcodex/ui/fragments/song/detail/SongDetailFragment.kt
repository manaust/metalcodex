package be.satanica.metalcodex.ui.fragments.song.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnLayout
import androidx.core.view.isGone
import androidx.navigation.fragment.navArgs
import be.satanica.metalcodex.R
import be.satanica.metalcodex.common.helpers.IntentHelper
import be.satanica.metalcodex.data.repository.LyricsRepository
import be.satanica.metalcodex.data.repository.SongRepository
import be.satanica.metalcodex.databinding.FragmentSongDetailBinding
import be.satanica.metalcodex.ui.fragments.base.BaseScreenFragment
import be.satanica.metalcodex.ui.fragments.song.SongsViewModel
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class SongDetailFragment : BaseScreenFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            SongDetailFragment()
    }

    override val iconRes: Int
        get() = R.drawable.ic_back_arrow_white

    private lateinit var binding: FragmentSongDetailBinding
    private val args: SongDetailFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val songsViewModel = SongsViewModel(SongRepository.getInstance(context))
        val lyricsViewModel = LyricsViewModel(LyricsRepository.getInstance())
        binding = FragmentSongDetailBinding.inflate(inflater, container, false)

        Glide.with(this)
            .load(args.thumbnail)
            .into(binding.thumbnail)

        setBookmarked(args.bookmarked)

        binding.header.doOnLayout {
            binding.svLyrics.setPadding(
                0,
                it.bottom - resources.getDimension(R.dimen.margin_large).toInt(),
                0,
                0
            )
        }

        addSubscription(
            lyricsViewModel.getLyrics(String.format("%s.txt", args.id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setLyrics, this::setError)
        )

        binding.tvSong.text = args.name
        binding.tvArtist.text = args.artist
        binding.tvAlbum.text = args.album
        binding.tvUrl.setOnClickListener {
            startActivity(
                IntentHelper.spotifyIntent(
                    requireActivity().packageManager,
                    "track",
                    args.id
                )
            )
        }

        binding.ivBookmark.setOnClickListener {
            addSubscription(songsViewModel.setBookmarked(args.id, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { setBookmarked(true) })
        }

        binding.ivBookmarkActive.setOnClickListener {
            addSubscription(songsViewModel.setBookmarked(args.id, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { setBookmarked(false) })
        }

        return binding.root
    }

    private fun setError(error: Throwable) {
        Timber.e(error)
        Toast.makeText(
            context,
            "Could not load lyrics. Please check your connection.",
            Toast.LENGTH_SHORT
        ).show()
        binding.skeleton.isGone = true
    }

    private fun setLyrics(lyrics: String) {
        binding.skeleton.isGone = true
        binding.tvLyrics.isGone = false
        binding.tvLyrics.text = lyrics
    }

    private fun setBookmarked(bookmarked: Boolean) {
        binding.ivBookmark.isGone = bookmarked
        binding.ivBookmarkActive.isGone = !bookmarked
    }
}
