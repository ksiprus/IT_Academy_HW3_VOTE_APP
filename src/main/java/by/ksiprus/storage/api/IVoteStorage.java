package by.ksiprus.storage.api;

import by.ksiprus.dto.Vote;
import java.util.List;

public interface IVoteStorage {
    void add(Vote vote);

    List<Vote> getAll();
}
