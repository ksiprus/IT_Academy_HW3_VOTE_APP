package by.ksiprus.service;

import by.ksiprus.dto.Stats;
import by.ksiprus.dto.Vote;
import by.ksiprus.service.api.IVoteService;
import by.ksiprus.storage.VoteStorageRam;
import by.ksiprus.storage.api.IVoteStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteService implements IVoteService {
    private static final IVoteStorage storage = new VoteStorageRam();

    @Override
    public void add(Vote vote) {
        this.storage.add(vote);
    }

    @Override
    public Stats getStats() {

        Map<String, Integer> artistStats = new HashMap<>();
        Map<String, Integer> genresStats = new HashMap<>();
        List<String> abouts = new ArrayList<>();

        List<Vote> all = storage.getAll();

        for (Vote vote : all) {
            artistStats.compute(vote.getArtist(), (key, value)
                    -> value == null ? 1 : value + 1);
            for (String genre : vote.getGenres()) {
                genresStats.compute(genre, (key, value)
                        -> value == null ? 1 : value + 1);
            }
            abouts.add(vote.getAbout() + ": " + vote.getDtCreate());
        }
        return new Stats(artistStats, genresStats, abouts);
    }
}