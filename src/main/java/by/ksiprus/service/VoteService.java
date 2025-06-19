package by.ksiprus.service;

import by.ksiprus.dto.Vote;
import by.ksiprus.dto.Stats;
import by.ksiprus.service.api.IVoteService;
import by.ksiprus.storage.IVoteStorage;

import java.util.*;

public class VoteService implements IVoteService {
    private final IVoteStorage storage;

    public VoteService(IVoteStorage storage) {
        this.storage = storage;
    }

    @Override
    public void addVote(Vote vote) {
        storage.add(vote);
    }

    @Override
    public Stats getStats() {
        List<Vote> votes = storage.getAll();

        Map<String, Integer> authors = new HashMap<>();
        Map<String, Integer> genres = new HashMap<>();
        List<String> abouts = new ArrayList<>();

        for (Vote vote : votes) {
            authors.merge(vote.getArtist(), 1, Integer::sum);
            genres.merge(vote.getGenre(), 1, Integer::sum);
            if (vote.getAbout() != null && !vote.getAbout().isBlank()) {
                abouts.add(vote.getAbout());
            }
        }

        return new Stats(authors, genres, abouts);
    }
}
