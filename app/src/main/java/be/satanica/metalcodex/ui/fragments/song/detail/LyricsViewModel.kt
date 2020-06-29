package be.satanica.metalcodex.ui.fragments.song.detail

import androidx.lifecycle.ViewModel
import be.satanica.metalcodex.data.repository.LyricsRepository
import io.reactivex.Observable

class LyricsViewModel(lyricsRepository: LyricsRepository) : ViewModel() {

    private var repository: LyricsRepository = lyricsRepository

    fun getLyrics(id: String): Observable<String> {
        return repository.fetchLyrics(id)
    }
}
