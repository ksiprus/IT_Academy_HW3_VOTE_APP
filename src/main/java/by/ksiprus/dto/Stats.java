package by.ksiprus.dto;

import java.util.List;
import java.util.Map;

public class Stats {
    private Map<String, Integer> artists;
    private Map<String, Integer> genres;
    private List<String> abouts;

    public Stats(Map<String, Integer> artists, Map<String, Integer> genres, List<String> abouts) {
        this.artists = artists;
        this.genres = genres;
        this.abouts = abouts;
    }

    public Map<String, Integer> getArtists() {
        return artists;
    }

    public Map<String, Integer> getGenres() {
        return genres;
    }

    public List<String> getAbouts() {
        return abouts;
    }
}

