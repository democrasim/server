package com.lawsystem.lawserver.repo;

import java.util.List;
import java.util.Optional;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawVote;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.VoteType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LawVoteRepository extends MongoRepository<LawVote, String> {

    Optional<LawVote> getByLawAndVoter(Law law, Member member);

    List<LawVote> findAllByLaw(Law law);

    int countAllByLawAndVote(Law law, VoteType type);
}
