package com.lawsystem.lawserver.controller;


import com.lawsystem.lawserver.dto.BanLawProposition;
import com.lawsystem.lawserver.dto.FactLawProposition;
import com.lawsystem.lawserver.dto.LawProposition;
import com.lawsystem.lawserver.exception.LawNotUnderVoteException;
import com.lawsystem.lawserver.exception.UnregisteredMemberException;
import com.lawsystem.lawserver.model.*;
import com.lawsystem.lawserver.model.content.BanContent;
import com.lawsystem.lawserver.model.content.FactContent;
import com.lawsystem.lawserver.repo.LawContentRepository;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.LawVoteRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="laws")
public class LawController {

    final
    MemberRepository memberRepository;

    final
    LawRepository lawRepository;

    final
    LawVoteRepository lawVoteRepository;

    final
    LawContentRepository lawContentRepository;

    public LawController(LawRepository repository, LawVoteRepository lawVoteRepository, MemberRepository memberRepository, LawContentRepository lawContentRepository) {
        this.lawRepository = repository;
        this.lawVoteRepository = lawVoteRepository;
        this.memberRepository = memberRepository;
        this.lawContentRepository = lawContentRepository;
    }


    @GetMapping(path="passed_paged")
    public @ResponseBody
    Page<Law> getAllPassedLaws(int page, int limit) {
        return lawRepository.findAllByStatus(LawStatus.PASSED, PageRequest.of(page - 1, limit));
    }
    @GetMapping(path="passed")
    public @ResponseBody
    List<Law> getAllPassedLaws() {
        return lawRepository.findAllByStatus(LawStatus.PASSED);
    }



    @PostMapping(path="propose_ban")
    public @ResponseBody Law proposeBan(BanLawProposition proposition) throws UnregisteredMemberException {
        Member member = memberRepository.findById(proposition.getLegislator()).orElseThrow(IllegalArgumentException::new);

        if(!member.isRegistered()) throw new UnregisteredMemberException();
        Law law = proposition.toLaw();

        BanContent content = new BanContent();

        law.setLegislator(member);
        content.setDescription(proposition.getBan());

        lawContentRepository.save(content);
        lawRepository.save(law);

        return law;
    }

    @PostMapping(path="propose_fact")
    public @ResponseBody Law proposeFact(FactLawProposition proposition) throws UnregisteredMemberException {
        Member member = memberRepository.findById(proposition.getLegislator()).orElseThrow(IllegalArgumentException::new);

        if(!member.isRegistered()) throw new UnregisteredMemberException();
        Law law = proposition.toLaw();

        FactContent content = new FactContent();

        law.setLegislator(member);
        content.setDescription(proposition.getFact());

        lawContentRepository.save(content);
        lawRepository.save(law);

        return law;
    }


    @PutMapping(path="vote")
    public @ResponseBody Law vote(long law, long member, VoteType type, String reason) throws UnregisteredMemberException, LawNotUnderVoteException {
        Law lawObject = lawRepository
                .findById(law)
                .orElseThrow(IllegalArgumentException::new);
        Member memberObject = memberRepository.findById(member).orElseThrow(IllegalArgumentException::new);

        if(!memberObject.isRegistered()) throw new UnregisteredMemberException();
        if(lawObject.getStatus() != LawStatus.UNDER_VOTE) throw new LawNotUnderVoteException();

        Optional<LawVote> voteOptional = lawObject
                .getVotes()
                .stream()
                .filter(vote -> vote.getVoter().equals(memberObject))
                .findAny();

        if(voteOptional.isPresent()) {
            LawVote vote = voteOptional.get();
            vote.setVote(type);

        } else {
            LawVote vote = new LawVote();
            vote.setLaw(lawObject);
            vote.setVote(type);
            vote.setReason(reason);

            lawVoteRepository.save(vote);
            lawObject.getVotes().add(vote);
        }

        return lawObject;
    }

}
