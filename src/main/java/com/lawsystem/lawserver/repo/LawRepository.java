package com.lawsystem.lawserver.repo;

import java.util.Date;
import java.util.List;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawStatus;
import com.lawsystem.lawserver.model.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawRepository extends MongoRepository<Law, String> {

    List<Law> findAllByLegislator(Member user);

    List<Law> findAllByLegislator(Member user, Pageable pageable);

    List<Law> findAllByStatus(LawStatus status);

    List<Law> findAllByStatus(LawStatus status, Pageable pageable);

    List<Law> findAllByTimestampBeforeAndStatus(Date timestamp, LawStatus status);

    List<Law> findAllByResolveTimeBeforeAndStatus(Date resolveTime, LawStatus status);

}
