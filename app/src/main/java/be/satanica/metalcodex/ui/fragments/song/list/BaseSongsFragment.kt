package be.satanica.metalcodex.ui.fragments.song.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import be.satanica.metalcodex.data.models.Song
import be.satanica.metalcodex.data.repository.SongRepository
import be.satanica.metalcodex.databinding.FragmentSongsListBinding
import be.satanica.metalcodex.ui.fragments.base.BaseFragment
import be.satanica.metalcodex.ui.fragments.song.SongAdapter
import be.satanica.metalcodex.ui.fragments.song.SongsViewModel
import timber.log.Timber

abstract class BaseSongsFragment : BaseFragment() {

    private lateinit var binding: FragmentSongsListBinding
    private lateinit var adapter: SongAdapter
    protected lateinit var songsViewModel: SongsViewModel

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongsListBinding.inflate(inflater, container, false)
        adapter = SongAdapter()
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = adapter

        songsViewModel = SongsViewModel(SongRepository.getInstance(context))
        return binding.root
    }

    protected fun setError(error: Throwable) {
        Timber.e(error)
        Toast.makeText(
            context,
            "Could not load songs. Please check your connection.",
            Toast.LENGTH_SHORT
        ).show()
        binding.skeleton.isGone = true
        binding.noResults.isGone = false
        binding.list.isGone = true
    }

    protected fun setItems(items: List<Song>) {
        if (items.isEmpty()) {
            binding.list.isGone = true
            binding.noResults.isGone = false
            binding.skeleton.isGone = true
        } else {
            adapter.updateItems(items)
            binding.list.isGone = false
            binding.noResults.isGone = true
            binding.skeleton.isGone = true
        }
    }
}
