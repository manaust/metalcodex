package be.satanica.metalcodex.data.database.daos;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import be.satanica.metalcodex.data.models.DbSong;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public abstract class SongDao extends BaseDao<DbSong> {

    @Query("SELECT * FROM songs WHERE id = :id AND bookmarked = 1 ORDER BY name ASC")
    abstract DbSong getBookmarkedSong(String id);

    @Query("SELECT * FROM songs ORDER BY name ASC")
    public abstract Single<List<DbSong>> loadAllSongs();

    @Query("SELECT * FROM songs WHERE bookmarked = 1 ORDER BY name ASC")
    public abstract Single<List<DbSong>> loadBookmarkedSongs();

    @Query("SELECT * FROM songs WHERE name LIKE :term OR artist LIKE :term")
    public abstract Single<List<DbSong>> searchSongs(String term);

    @Query("UPDATE songs SET bookmarked = :bookmarked WHERE id LIKE :id")
    public abstract Single<Integer> setBookmarked(String id, boolean bookmarked);

    @Query("DELETE FROM songs")
    public abstract void deleteAllSongs();

    @Transaction
    public void insertSongs(List<DbSong> songs) {
        // Retain bookmarks for existing songs
        songs.forEach(song -> {
            if (getBookmarkedSong(song.id) != null) song.setBookmarked(true);
        });
        deleteAllSongs();
        insert(songs);
    }

    public Completable completableInsertSongs(List<DbSong> songs) {
        return Completable.fromAction(() -> insertSongs(songs));
    }
}
