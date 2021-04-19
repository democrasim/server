package com.lawsystem.lawserver.controller;


import com.lawsystem.lawserver.dto.BanLawProposition;
import com.lawsystem.lawserver.dto.FactLawProposition;
import com.lawsystem.lawserver.dto.LawProposition;
import com.lawsystem.lawserver.dto.RequirementLawProposition;
import com.lawsystem.lawserver.exception.LawNotUnderVoteException;
import com.lawsystem.lawserver.exception.UnregisteredMemberException;
import com.lawsystem.lawserver.model.*;
import com.lawsystem.lawserver.model.content.BanContent;
import com.lawsystem.lawserver.model.content.FactContent;
import com.lawsystem.lawserver.repo.LawContentRepository;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.LawVoteRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.service.LawService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping(path="laws")
public class LawController {

    final LawService lawService;

    @GetMapping(path="passed", params = {"page", "limit"})
    public @ResponseBody Page<Law> getAllPassedLaws(int page, int limit) {
        return lawService.getAllPassedLaws(page,limit);
    }

    @GetMapping(path="passed")
    public @ResponseBody List<Law> getAllPassedLaws() {
        return lawService.getAllPassedLaws();
    }

    @PostMapping(path="propose_ban")
    public @ResponseBody Law proposeBan(BanLawProposition proposition) throws UnregisteredMemberException {
        return lawService.proposeLaw(proposition, true);
    }

    @PostMapping(path="propose_fact")
    public @ResponseBody Law proposeFact(FactLawProposition proposition) throws UnregisteredMemberException {
        return lawService.proposeLaw(proposition, true);
    }

    @PostMapping(path="propose_requirement")
    public @ResponseBody Law proposeRequirement(RequirementLawProposition proposition) throws UnregisteredMemberException {
        return lawService.proposeLaw(proposition, true);
    }



    @PutMapping(path="vote")
    public @ResponseBody Law vote(long law, long member, VoteType type, String reason) throws UnregisteredMemberException, LawNotUnderVoteException {
        return lawService.vote(law, member, type, reason);
    }

}
