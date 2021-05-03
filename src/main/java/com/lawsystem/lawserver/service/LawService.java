package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.dto.LawProposition;
import com.lawsystem.lawserver.exception.LawNotUnderVoteException;
import com.lawsystem.lawserver.exception.UnregisteredMemberException;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawStatus;
import com.lawsystem.lawserver.model.LawVote;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.VoteType;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.repo.LawContentRepository;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.LawVoteRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LawService {

    final MemberRepository memberRepository;
    final LawVoteRepository lawVoteRepository;
    final LawRepository lawRepository;
    final LawContentRepository lawContentRepository;

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

        LawVote lawVote = lawObject.getVotes()
            .stream()
            .filter(vote -> vote.getVoter().equals(memberObject))
            .findAny()
            .map(vote -> vote.setVote(type))
            .orElseGet(() -> {
                LawVote vote = new LawVote();
                vote.setLaw(lawObject);
                vote.setVoter(memberObject);
                vote.setVote(type);
                vote.setReason(reason);
                lawObject.getVotes().add(vote);
                return vote;
            });

        lawVoteRepository.save(lawVote);

        return lawObject;
    }

    public Law proposeLaw(LawProposition proposition, boolean requiresRegistration) throws UnregisteredMemberException {
        Member member = memberRepository.findById(proposition.getLegislator()).orElseThrow(IllegalArgumentException::new);

        if (!member.isRegistered() && requiresRegistration) {
            throw new UnregisteredMemberException();
        }

        Law law = proposition.toLaw();
        law.setContent(proposition.getContent());

        law.setLegislator(member);



        lawRepository.save(law);

        return law;
    }

    public List<Law> getAllLawsByStatus(LawStatus status, int page, int limit) {
        return lawRepository.findAllByStatus(status, PageRequest.of(page - 1, limit));
    }


    public List<Law> getAllLawsByStatus(LawStatus status) {
        return lawRepository.findAllByStatus(status);
    }


}
