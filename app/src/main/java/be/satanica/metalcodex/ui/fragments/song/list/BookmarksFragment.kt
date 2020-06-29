package be.satanica.metalcodex.ui.fragments.song.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class BookmarksFragment : BaseSongsFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            BookmarksFragment()
    }

    override fun subscriptions() = arrayOf(
        songsViewModel.getBookmarkedSongs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setItems, this::setError)
    )
}
