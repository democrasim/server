package com.lawsystem.lawserver.service;

import java.util.Date;
import java.util.List;

import com.lawsystem.lawserver.controller.LawController;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawStatus;
import com.lawsystem.lawserver.model.VoteType;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.LawVoteRepository;
import com.lawsystem.lawserver.service.law_executors.MainExecutor;
import com.lawsystem.lawserver.util.DataConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class LawCheckService {

    private final MainExecutor executor;
    private final LawRepository lawRepository;
    private final LawVoteRepository lawVoteRepository;
    private final WhatsAppService whatsAppService;
    private final DataConverter dataConverter;

    @Scheduled(fixedRate = 10000)
    public void checkLaws() {
        List<Law> laws = lawRepository.findAllByResolveTimeBeforeAndStatus(new Date(), LawStatus.UNDER_VOTE);
        laws.forEach(
                law -> {
                    int supporting = lawVoteRepository.countAllByLawAndVote(law, VoteType.FOR);
                    int against = lawVoteRepository.countAllByLawAndVote(law, VoteType.AGAINST);
                    double maximumMinMajority = 0;
                    for (LawContent content : law.getContent()) {
                        maximumMinMajority = Math.max(executor.getMinMajority(content.getClass()), maximumMinMajority);
                    }
                    if ((double) supporting / (supporting + against) > maximumMinMajority) {
                        law.setStatus(LawStatus.PASSED);
                        law.getContent().forEach(executor::execute);
                    } else {
                        law.setStatus(LawStatus.FAILED);
                        law.getContent().forEach(executor::failed);
                    }


                    lawRepository.save(law);

                    whatsAppService.sendFinishedLaw(law);

                }
        );
    }

}
