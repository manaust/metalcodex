package be.satanica.metalcodex.data.models;

import be.satanica.metalcodex.common.utilities.ReversibleMapper;

public class ApiSongMapper extends ReversibleMapper<Song, ApiSong> {

    @Override
    public ApiSong map(Song input) {
        return new ApiSong(
                input.id,
                input.name,
                input.artist,
                input.album,
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
    public Song reverseMap(ApiSong input) {
        return new Song(
                input.id,
                input.name,
                input.artist,
                input.album,
                false,
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
