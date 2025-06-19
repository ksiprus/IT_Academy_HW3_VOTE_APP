package by.ksiprus.service.api;

import by.ksiprus.dto.Vote;
import by.ksiprus.dto.Stats;

public interface IVoteService {
    void addVote(Vote vote);

    Stats getStats();
}