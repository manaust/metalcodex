package be.satanica.metalcodex.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import be.satanica.metalcodex.data.database.daos.SongDao;
import be.satanica.metalcodex.data.models.DbSong;

@Database(entities = {DbSong.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SongDao songDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "song_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
