package by.ksiprus.service.api;

import by.ksiprus.dto.Stats;
import by.ksiprus.dto.Vote;

public interface IVoteService {
    void add(Vote vote);

    Stats getStats();
}