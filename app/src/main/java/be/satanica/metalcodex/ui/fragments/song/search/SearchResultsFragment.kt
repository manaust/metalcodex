package be.satanica.metalcodex.ui.fragments.song.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import be.satanica.metalcodex.R
import be.satanica.metalcodex.data.models.Song
import be.satanica.metalcodex.data.repository.SongRepository
import be.satanica.metalcodex.databinding.FragmentSearchResultsBinding
import be.satanica.metalcodex.ui.fragments.base.BaseScreenFragment
import be.satanica.metalcodex.ui.fragments.song.SongAdapter
import be.satanica.metalcodex.ui.fragments.song.SongsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchResultsFragment : BaseScreenFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchResultsFragment()
    }

    override val iconRes: Int
        get() = R.drawable.ic_back_arrow

    private lateinit var binding: FragmentSearchResultsBinding
    private lateinit var adapter: SongAdapter
    private val args: SearchResultsFragmentArgs by navArgs()

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        binding.tvSearchTerm.text = String.format(getString(R.string.search_term, args.term))
        adapter = SongAdapter()
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = adapter

        val songsViewModel = SongsViewModel(SongRepository.getInstance(context))
        songsViewModel.searchSongs(String.format("%%%s%%", args.term))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setItems, Timber::e)

        return binding.root
    }

    private fun setItems(items: List<Song>) {
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
