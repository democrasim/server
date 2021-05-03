package com.lawsystem.lawserver.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawsystem.lawserver.dto.*;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping(path="laws")
public class LawController {

    final LawService lawService;
    final ModelMapper modelMapper;


    @GetMapping(path="with_status/{status}/", params = {"page", "limit"})
    public @ResponseBody List<LawDto> getAllLawsByStatus(@PathVariable("status") LawStatus status, int page, int limit) {
        return lawsToDtos(lawService.getAllLawsByStatus(status, page, limit));
    }

    @GetMapping(path="with_status/{status}/")
    public @ResponseBody List<LawDto> getAllLawsByStatus(@PathVariable("status") LawStatus status) {
        return lawsToDtos(lawService.getAllLawsByStatus(status));
    }

    private List<LawDto> lawsToDtos(List<Law> laws) {
        return laws.stream()
                .map(law -> modelMapper.map(law, LawDto.class).setContent(law.getContent()))
                .peek(law -> law.setContentString(law.getContent().toString()))
                .collect(Collectors.toList());
    }

    @GetMapping(path="passed", params = {"page", "limit"})
    public @ResponseBody List<LawDto> getAllPassedLaws(int page, int limit) {

        return lawsToDtos(lawService
                .getAllLawsByStatus(LawStatus.PASSED, page,limit));

    }

    @GetMapping(path="passed")
    public @ResponseBody List<LawDto> getAllPassedLaws() {
        return lawsToDtos(lawService.getAllLawsByStatus(LawStatus.PASSED));
    }

    @PostMapping(path="propose_ban")
    public @ResponseBody LawDto proposeBan(@RequestBody BanLawProposition proposition) throws UnregisteredMemberException {
        return modelMapper.map(lawService.proposeLaw(proposition, true), LawDto.class);
    }

    @PostMapping(path="propose_fact")
    public @ResponseBody LawDto proposeFact(@RequestBody FactLawProposition proposition) throws UnregisteredMemberException {
        return modelMapper.map(lawService.proposeLaw(proposition, true), LawDto.class);
    }

    @PostMapping(path="propose_requirement")
    public @ResponseBody LawDto proposeRequirement(@RequestBody RequirementLawProposition proposition) throws UnregisteredMemberException {
        return modelMapper.map(lawService.proposeLaw(proposition, true), LawDto.class);
    }

    @PutMapping(path="vote")
    public @ResponseBody Law vote(@RequestBody VoteDto vote) throws UnregisteredMemberException, LawNotUnderVoteException {
        return lawService.vote(vote.getLaw(),vote.getMember(), vote.getType(), vote.getReason());
    }

}
