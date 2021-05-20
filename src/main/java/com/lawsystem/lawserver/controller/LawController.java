package com.lawsystem.lawserver.controller;


import java.util.List;
import java.util.stream.Collectors;

import com.lawsystem.lawserver.dto.LawDto;
import com.lawsystem.lawserver.dto.LawProposition;
import com.lawsystem.lawserver.dto.VoteDto;
import com.lawsystem.lawserver.exception.LawNotUnderVoteException;
import com.lawsystem.lawserver.exception.UnregisteredMemberException;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawStatus;
import com.lawsystem.lawserver.service.LawService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping(path="laws")
public class LawController {

    final LawService lawService;
    final ModelMapper modelMapper;


    @GetMapping(path="with_status/{status}/", params = {"page", "limit"})
    public @ResponseBody List<LawDto> getAllLawsByStatus(@PathVariable("status") LawStatus status, int page, int limit) {
        return lawsToDataTransferObjects(lawService.getAllLawsByStatus(status, page, limit));
    }

    @GetMapping(path="with_status/{status}/")
    public @ResponseBody List<LawDto> getAllLawsByStatus(@PathVariable("status") LawStatus status) {
        return lawsToDataTransferObjects(lawService.getAllLawsByStatus(status));
    }

    private List<LawDto> lawsToDataTransferObjects(List<Law> laws) {
        return laws.stream()
                .map(law -> modelMapper.map(law, LawDto.class).setContent(law.getContent()))
                .peek(law -> law.setContentString(law.getContent().toString()))
                .collect(Collectors.toList());
    }

    @GetMapping(path="passed", params = {"page", "limit"})
    public @ResponseBody List<LawDto> getAllPassedLaws(int page, int limit) {

        return lawsToDataTransferObjects(lawService
                .getAllLawsByStatus(LawStatus.PASSED, page,limit));

    }

    @GetMapping(path="not_voted")
    public @ResponseBody List<LawDto> getAllNotVotedLaws(String userId) {
        return lawsToDataTransferObjects(lawService.getAllUnvotedLaws(userId));
    }

    @GetMapping(path="passed")
    public @ResponseBody List<LawDto> getAllPassedLaws() {
        return lawsToDataTransferObjects(lawService.getAllLawsByStatus(LawStatus.PASSED));
    }

    @PostMapping(path = "propose")
    public @ResponseBody LawDto propose(@RequestBody LawProposition proposition) throws UnregisteredMemberException {
        return modelMapper.map(lawService.proposeLaw(proposition), LawDto.class);
    }

    @PutMapping(path="vote")
    public @ResponseBody Law vote(@RequestBody VoteDto vote) throws UnregisteredMemberException, LawNotUnderVoteException {
        return lawService.vote(vote.getLaw(),vote.getMember(), vote.getType(), vote.getReason());
    }

}
