package be.satanica.metalcodex.services;

import java.util.List;

import be.satanica.metalcodex.data.models.ApiSong;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface SongApi {
    @GET("songs.json")
    Single<List<ApiSong>> fetchAllSongs();
}
