package be.satanica.metalcodex.data.repository;

import android.content.Context;

import java.util.List;

import be.satanica.metalcodex.data.database.AppDatabase;
import be.satanica.metalcodex.data.database.daos.SongDao;
import be.satanica.metalcodex.data.models.ApiSongMapper;
import be.satanica.metalcodex.data.models.DbSongMapper;
import be.satanica.metalcodex.data.models.Song;
import be.satanica.metalcodex.services.RetrofitClientInstance;
import be.satanica.metalcodex.services.SongApi;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SongRepository {

    private static SongRepository instance;
    private SongDao songDao;
    private SongApi songApi;
    private ApiSongMapper apiSongMapper;
    private DbSongMapper dbSongMapper;

    private SongRepository(SongDao songDao, SongApi songApi, ApiSongMapper apiSongMapper, DbSongMapper dbSongMapper) {
        this.songDao = songDao;
        this.songApi = songApi;
        this.apiSongMapper = apiSongMapper;
        this.dbSongMapper = dbSongMapper;
    }

    public static SongRepository getInstance(Context context) {
        if (instance == null) {
            instance = new SongRepository(
                    AppDatabase.getDatabase(context.getApplicationContext()).songDao(),
                    RetrofitClientInstance
                            .getRetrofitInstance()
                            .create(SongApi.class),
                    new ApiSongMapper(),
                    new DbSongMapper()
            );
        }

        return instance;
    }

    public Observable<List<Song>> getSongs() {
        return Observable.concat(getAllSongs(), fetchAllSongs());
    }

    public Observable<List<Song>> fetchAllSongs() {
        return songApi.fetchAllSongs().map(apiSongMapper::reverseMap)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .doOnNext(this::refreshSongs)
                .doOnError(Timber::e)
                .onErrorResumeNext(getAllSongs());
    }

    public Observable<List<Song>> getAllSongs() {
        return songDao.loadAllSongs().map(dbSongMapper::reverseMap).toObservable();
    }

    public Observable<List<Song>> getBookmarkedSongs() {
        return songDao.loadBookmarkedSongs().map(dbSongMapper::reverseMap).toObservable();
    }

    public Observable<Integer> setBookmarked(String id, boolean bookmarked) {
        return songDao.setBookmarked(id, bookmarked).toObservable();
    }

    public Observable<List<Song>> searchSongs(String term) {
        return songDao.searchSongs(term).map(dbSongMapper::reverseMap).toObservable();
    }

    public Completable insertSong(Song song) {
        return songDao.completableInsert(dbSongMapper.map(song));
    }

    public void insertSongs(List<Song> songs) {
        songDao.completableInsert(dbSongMapper.map(songs)).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe();
    }

    private void refreshSongs(List<Song> songs) {
        songDao.completableInsertSongs(dbSongMapper.map(songs))
                .subscribeOn(Schedulers.io()).subscribe();
    }
}
