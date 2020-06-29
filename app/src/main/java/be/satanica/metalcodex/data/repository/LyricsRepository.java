package be.satanica.metalcodex.data.repository;

import be.satanica.metalcodex.services.LyricsApi;
import be.satanica.metalcodex.services.RetrofitClientInstance;
import io.reactivex.Observable;

public class LyricsRepository {

    private static LyricsRepository instance;
    private LyricsApi lyricsApi;

    private LyricsRepository(LyricsApi lyricsApi) {
        this.lyricsApi = lyricsApi;
    }

    public static LyricsRepository getInstance() {
        if (instance == null) {
            instance = new LyricsRepository(
                    RetrofitClientInstance
                            .getRetrofitInstance()
                            .create(LyricsApi.class)
            );
        }

        return instance;
    }

    // Get items from API
    public Observable<String> fetchLyrics(String id) {
        return lyricsApi.fetchLyrics(id).toObservable();
    }
}
