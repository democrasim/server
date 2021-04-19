package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawVote;
import com.lawsystem.lawserver.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LawVoteRepository extends CrudRepository<LawVote, Long> {

    public Optional<LawVote> getByLawAndVoter(Law law, Member member);
    public List<LawVote> findAllByLaw(Law law);

}
