package be.satanica.metalcodex.data.models;

import be.satanica.metalcodex.common.utilities.ReversibleMapper;

public class DbSongMapper extends ReversibleMapper<Song, DbSong> {

    @Override
    public DbSong map(Song input) {
        return new DbSong(
                input.id,
                input.name,
                input.artist,
                input.album,
                input.bookmarked,
                input.duration,
                input.explicit,
                input.popularity,
                input.releaseDate,
                input.thumbnailLarge,
                input.thumbnailMedium,
                input.thumbnailSmall
        );
    }

    @Override
    public Song reverseMap(DbSong input) {
        return new Song(
                input.id,
                input.name,
                input.artist,
                input.album,
                input.bookmarked,
                input.duration,
                input.explicit,
                input.popularity,
                input.releaseDate,
                input.thumbnailLarge,
                input.thumbnailMedium,
                input.thumbnailSmall
        );
    }
}
