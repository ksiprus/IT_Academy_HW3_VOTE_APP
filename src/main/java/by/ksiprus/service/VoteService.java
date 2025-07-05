package by.ksiprus.service;

import by.ksiprus.dto.Stats;
import by.ksiprus.dto.Vote;
import by.ksiprus.service.api.IVoteService;
import by.ksiprus.storage.VoteStoragePostgres;
import by.ksiprus.storage.api.IVoteStorage;

import java.util.*;

public class VoteService implements IVoteService {
    private IVoteStorage storage;

    public VoteService() {
        this.storage = new VoteStoragePostgres();
    }

    @Override
    public void add(Vote vote) {
        if (vote == null) {
            throw new IllegalArgumentException("Голос не может быть null");
        }
        this.storage.add(vote);
    }

    @Override
    public Stats getStats() {
        Map<String, Integer> artistStats = new HashMap<>();
        Map<String, Integer> genresStats = new HashMap<>();
        List<String> abouts = new ArrayList<>();

        List<Vote> all = storage.getAll();

        for (Vote vote : all) {
            // Статистика по артистам
            artistStats.compute(vote.getArtist(), (key, value) -> value == null ? 1 : value + 1);

            // Статистика по жанрам
            List<String> genres = vote.getGenres() != null ? vote.getGenres() : Collections.emptyList();
            for (String genre : genres) {
                genresStats.compute(genre, (key, value) -> value == null ? 1 : value + 1);
            }


            abouts.add(String.format("About: %s, Date: %s", vote.getAbout(), vote.getDtCreate()));
        }

        return new Stats(artistStats, genresStats, abouts);
    }
}
