package be.satanica.metalcodex.ui.fragments.song.list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SongsFragment : BaseSongsFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            SongsFragment()
    }

    override fun subscriptions() = arrayOf(
        songsViewModel.getSongs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setItems, this::setError)
    )
}
