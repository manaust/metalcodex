package be.satanica.metalcodex.data.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "songs")
public class DbSong {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "artist")
    public String artist;

    @NonNull
    @ColumnInfo(name = "album")
    public String album;

    @ColumnInfo(name = "bookmarked")
    public boolean bookmarked;

    @ColumnInfo(name = "duration")
    public int duration;

    @ColumnInfo(name = "explicit")
    public boolean explicit;

    @ColumnInfo(name = "popularity")
    public int popularity;

    @NonNull
    @ColumnInfo(name = "release_date")
    public String releaseDate;

    @NonNull
    @ColumnInfo(name = "thumbnail_large")
    public String thumbnailLarge;

    @NonNull
    @ColumnInfo(name = "thumbnail_medium")
    public String thumbnailMedium;

    @NonNull
    @ColumnInfo(name = "thumbnail_small")
    public String thumbnailSmall;

    public DbSong(
            @NonNull String id,
            @NonNull String name,
            @NonNull String artist,
            @NonNull String album,
            boolean bookmarked,
            int duration,
            boolean explicit,
            int popularity,
            @NonNull String releaseDate,
            @NonNull String thumbnailLarge,
            @NonNull String thumbnailMedium,
            @NonNull String thumbnailSmall
    ) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.bookmarked = bookmarked;
        this.duration = duration;
        this.explicit = explicit;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.thumbnailLarge = thumbnailLarge;
        this.thumbnailMedium = thumbnailMedium;
        this.thumbnailSmall = thumbnailSmall;
    }

    // Getters
    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getArtist() {
        return artist;
    }

    @NonNull
    public String getAlbum() {
        return album;
    }

    public boolean getBookmarked() {
        return bookmarked;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public int getPopularity() {
        return popularity;
    }

    @NonNull
    public String getReleaseDate() {
        return releaseDate;
    }

    @NonNull
    public String getThumbnailLarge() {
        return thumbnailLarge;
    }

    @NonNull
    public String getThumbnailMedium() {
        return thumbnailMedium;
    }

    @NonNull
    public String getThumbnailSmall() {
        return thumbnailSmall;
    }

    // Setters
    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setArtist(@NonNull String artist) {
        this.artist = artist;
    }

    public void setAlbum(@NonNull String album) {
        this.album = album;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setReleaseDate(@NonNull String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setThumbnailLarge(@NonNull String thumbnailLarge) {
        this.thumbnailLarge = thumbnailLarge;
    }

    public void setThumbnailMedium(@NonNull String thumbnailMedium) {
        this.thumbnailMedium = thumbnailMedium;
    }

    public void setThumbnailSmall(@NonNull String thumbnailSmall) {
        this.thumbnailSmall = thumbnailSmall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbSong song = (DbSong) o;
        return duration == song.duration &&
                explicit == song.explicit &&
                popularity == song.popularity &&
                bookmarked == song.bookmarked &&
                id.equals(song.id) &&
                name.equals(song.name) &&
                artist.equals(song.artist) &&
                album.equals(song.album) &&
                releaseDate.equals(song.releaseDate) &&
                thumbnailLarge.equals(song.thumbnailLarge) &&
                thumbnailMedium.equals(song.thumbnailMedium) &&
                thumbnailSmall.equals(song.thumbnailSmall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, artist, album, bookmarked, duration, explicit, popularity, releaseDate, thumbnailLarge, thumbnailMedium, thumbnailSmall);
    }
}
