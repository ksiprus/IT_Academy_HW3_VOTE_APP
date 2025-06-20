package by.ksiprus.storage;

import by.ksiprus.dto.Vote;
import by.ksiprus.storage.api.IVoteStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VoteStorageRam implements IVoteStorage {
    private final List<Vote> vote = new ArrayList<>();

    @Override
    public void add(Vote vote) {
        this.vote.add(vote);
    }

    @Override
    public List<Vote> getAll() {
        return Collections.unmodifiableList(vote);
    }
}
