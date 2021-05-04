package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawStatus;
import com.lawsystem.lawserver.model.LawVote;
import com.lawsystem.lawserver.model.VoteType;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.model.content.ChangePresidentContent;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.LawVoteRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.service.law_executors.AddMemberExecutor;
import com.lawsystem.lawserver.service.law_executors.ChangePresidentExecutor;
import com.lawsystem.lawserver.service.law_executors.LawExecutor;
import com.lawsystem.lawserver.service.law_executors.MainExecutor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class LawCheckService {

    private final MainExecutor executor;
    private final LawRepository lawRepository;
    private final LawVoteRepository lawVoteRepository;

    @Scheduled(fixedRate = 10000)
    public void checkLaws() {
        long DAY_IN_MS = 1000;
        Date dayAgo = new Date(System.currentTimeMillis() - DAY_IN_MS);
        List<Law> laws = lawRepository.findAllByTimestampBeforeAndStatus(dayAgo, LawStatus.UNDER_VOTE);
        laws.forEach(
                law -> {
                    int supporting = lawVoteRepository.countAllByLawAndVote(law, VoteType.FOR);
                    int against = lawVoteRepository.countAllByLawAndVote(law, VoteType.AGAINST);
                    if (supporting / (supporting + against) >= executor.getMinMajority(law.getContent().getClass())) {
                        law.setStatus(LawStatus.PASSED);
                        executor.execute(law.getContent());
                    } else {
                        law.setStatus(LawStatus.FAILED);
                    }
                    lawRepository.save(law);
                }
        );
    }

}
