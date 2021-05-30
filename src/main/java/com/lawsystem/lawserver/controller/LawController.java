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
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping(path = "laws")
public class LawController {

    final LawService lawService;
    final ModelMapper modelMapper;


    @GetMapping(path = "with_status/{status}/", params = {"page", "limit"})
    public @ResponseBody
    List<LawDto> getAllLawsByStatus(@PathVariable("status") LawStatus status, int page, int limit, @RequestParam(required = false) String userId) {
        List<Law> laws = lawService.getAllLawsByStatus(status, page, limit);

        if (StringUtils.isBlank(userId)) {
            return lawsToDataTransferObjects(laws);
        }
        return lawsToDataTransferObjectsById(laws, userId);
    }

    @GetMapping(path = "with_status/{status}/")
    public @ResponseBody
    List<LawDto> getAllLawsByStatus(@PathVariable("status") LawStatus status, @RequestParam(required = false) String userId) {
        List<Law> laws = lawService.getAllLawsByStatus(status);

        if (StringUtils.isBlank(userId)) {
            return lawsToDataTransferObjects(laws);
        }
        return lawsToDataTransferObjectsById(laws, userId);
    }

    private List<LawDto> lawsToDataTransferObjects(List<Law> laws) {
        return laws.stream()
                .map(law -> modelMapper.map(law, LawDto.class).setContent(law.getContent()))
                .peek(law -> law.setContentString(law.getContent().toString()))
                .collect(Collectors.toList());
    }

    private List<LawDto> lawsToDataTransferObjectsById(List<Law> laws, String userId) {
        return laws.stream()
                .map(law -> modelMapper.map(law, LawDto.class).setContent(law.getContent()))
                .peek(law -> law.setContentString(law.getContent().toString()))
                .peek(law -> law.getVotes().stream().filter(vote -> vote.getVoter().getId().equals(userId)).findAny().ifPresent(law::setUserVote))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "not_voted")
    public @ResponseBody
    List<LawDto> getAllNotVotedLaws(String userId) {
        return lawsToDataTransferObjectsById(lawService.getAllUnvotedLaws(userId), userId);
    }

    @PostMapping(path = "propose")
    public @ResponseBody
    LawDto propose(@RequestBody LawProposition proposition) throws UnregisteredMemberException {
        return modelMapper.map(lawService.proposeLaw(proposition), LawDto.class);
    }

    @PutMapping(path = "vote")
    public @ResponseBody
    Law vote(@RequestBody VoteDto vote) throws UnregisteredMemberException, LawNotUnderVoteException {
        return lawService.vote(vote.getLaw(), vote.getMember(), vote.getType(), vote.getReason());
    }

}
