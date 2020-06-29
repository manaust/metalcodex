package be.satanica.metalcodex.ui.fragments.song

import androidx.lifecycle.ViewModel
import be.satanica.metalcodex.data.models.Song
import be.satanica.metalcodex.data.repository.SongRepository
import io.reactivex.Observable

class SongsViewModel(songRepository: SongRepository) : ViewModel() {

    private var repository: SongRepository = songRepository

    fun getSongs(): Observable<List<Song>> {
        return repository.songs
    }

    fun getBookmarkedSongs(): Observable<List<Song>> {
        return repository.bookmarkedSongs
    }

    fun searchSongs(term: String): Observable<List<Song>> {
        return repository.searchSongs(term)
    }

    fun setBookmarked(id: String, bookmarked: Boolean): Observable<Int> {
        return repository.setBookmarked(id, bookmarked)
    }
}
