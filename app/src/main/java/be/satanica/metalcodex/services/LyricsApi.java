package be.satanica.metalcodex.services;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface LyricsApi {
    @GET()
    Single<String> fetchLyrics(@Url String id);
}
