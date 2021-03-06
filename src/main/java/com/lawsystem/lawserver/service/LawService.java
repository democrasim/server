package com.lawsystem.lawserver.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.lawsystem.lawserver.dto.LawProposition;
import com.lawsystem.lawserver.exception.LawNotUnderVoteException;
import com.lawsystem.lawserver.exception.UnregisteredMemberException;
import com.lawsystem.lawserver.model.*;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.repo.LawContentRepository;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.LawVoteRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LawService {

    final MemberRepository memberRepository;
    final LawVoteRepository lawVoteRepository;
    final LawRepository lawRepository;
    final LawContentRepository lawContentRepository;
    final SequenceGeneratorService sequenceGeneratorService;
    final WhatsAppService whatsAppService;
    final VariableService variableService;

    public Law vote(String law, String member, VoteType type, String reason) throws UnregisteredMemberException, LawNotUnderVoteException {
        Law lawObject = lawRepository
                .findById(law)
                .orElseThrow(IllegalArgumentException::new);

        Member memberObject = memberRepository.findById(member).orElseThrow(IllegalArgumentException::new);

        if (!memberObject.isRegistered()) {
            throw new UnregisteredMemberException();
        }

        if (lawObject.getStatus() != LawStatus.UNDER_VOTE) {
            throw new LawNotUnderVoteException();
        }

        List<LawVote> votes = lawObject.getVotes();
        for (LawVote vote : votes) {
            if (vote.getVoter().equals(memberObject)) {
                vote.setVote(type);
                lawVoteRepository.save(vote);
                return lawObject;
            }
        }
        LawVote vote = new LawVote();
        vote.setLaw(lawObject);
        vote.setVoter(memberObject);
        vote.setVote(type);
        vote.setReason(reason);
        lawObject.getVotes().add(vote);
        lawVoteRepository.save(vote);
        lawRepository.save(lawObject);
        return lawObject;
    }

    public Law proposeLaw(LawProposition proposition) throws UnregisteredMemberException {
        Member member = memberRepository.findById(proposition.getLegislator()).orElseThrow(IllegalArgumentException::new);

        if (!member.isRegistered() && proposition.getContent().stream().noneMatch(lawContent -> lawContent instanceof AddMemberContent)) {
            throw new UnregisteredMemberException();
        }

        Law law = proposition.toLaw();

        law.setNumber(sequenceGeneratorService.generateSequence(Law.SEQUENCE_NAME));

        law.setLegislator(member);

        int time = variableService.getInstance().getTimeForLawsToPass();

        Date finished = new Date(law.getTimestamp().getTime() + time);

        law.setResolveTime(finished);

        law.setTitle(proposition.getTitle());

        law.setContent(proposition.getContent());

        lawRepository.save(law);

        whatsAppService.sendNewLaw(law);

        return law;
    }

    public List<Law> getAllLawsByStatus(LawStatus status, int page, int limit) {
        return lawRepository.findAllByStatus(status, PageRequest.of(page - 1, limit));
    }


    public List<Law> getAllLawsByStatus(LawStatus status) {
        return lawRepository.findAllByStatus(status);
    }


    public List<Law> getAllUnvotedLaws(String userId) {
        return lawRepository
                .findAllByStatus(LawStatus.UNDER_VOTE)
                .stream()
                .filter(law -> law
                        .getVotes()
                        .stream()
                        .allMatch(vote -> !vote
                                .getVoter()
                                .getId()
                                .equals(userId)))
                .collect(Collectors.toList());
    }


    public Law getByNumber(int number) {
        return lawRepository.getByNumber(number);
    }
    public Law get(String lawId) {
        return lawRepository.findById(lawId).orElse(null);
    }
}
