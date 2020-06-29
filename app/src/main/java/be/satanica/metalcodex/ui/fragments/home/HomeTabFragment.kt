package be.satanica.metalcodex.ui.fragments.home

import androidx.fragment.app.Fragment
import be.satanica.metalcodex.R
import be.satanica.metalcodex.ui.fragments.base.BaseTabFragment
import be.satanica.metalcodex.ui.fragments.song.list.BookmarksFragment
import be.satanica.metalcodex.ui.fragments.song.list.SongsFragment

class HomeTabFragment : BaseTabFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeTabFragment()
    }

    override val fragments = arrayOf<Fragment>(
        SongsFragment.newInstance(),
        BookmarksFragment.newInstance()
    )

    override val titleResources = intArrayOf(
        R.string.all_songs,
        R.string.bookmarks
    )
}
