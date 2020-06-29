package be.satanica.metalcodex.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Song implements Parcelable {
    @NonNull
    public String id;

    @NonNull
    public String name;

    @NonNull
    public String artist;

    @NonNull
    public String album;

    public boolean bookmarked;

    public int duration;

    public boolean explicit;

    public int popularity;

    @NonNull
    public String releaseDate;

    @NonNull
    public String thumbnailLarge;

    @NonNull
    public String thumbnailMedium;

    @NonNull
    public String thumbnailSmall;

    public Song(
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

    public Song(Parcel source) {
        id = source.readString();
        name = source.readString();
        artist = source.readString();
        album = source.readString();
        bookmarked = source.readByte() != 0;
        duration = source.readInt();
        explicit = source.readByte() != 0;
        popularity = source.readInt();
        releaseDate = source.readString();
        thumbnailLarge = source.readString();
        thumbnailMedium = source.readString();
        thumbnailSmall = source.readString();
    }

    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(artist);
        dest.writeString(album);
        dest.writeByte((byte) (bookmarked ? 1 : 0));
        dest.writeInt(duration);
        dest.writeByte((byte) (explicit ? 1 : 0));
        dest.writeInt(popularity);
        dest.writeString(releaseDate);
        dest.writeString(thumbnailLarge);
        dest.writeString(thumbnailMedium);
        dest.writeString(thumbnailSmall);
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel parcel) {
            return new Song(parcel);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return duration == song.duration &&
                explicit == song.explicit &&
                bookmarked == song.bookmarked &&
                popularity == song.popularity &&
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
