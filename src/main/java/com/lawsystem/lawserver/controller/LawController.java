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
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.service.LawService;
import com.lawsystem.lawserver.util.DataConverter;
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
    final DataConverter dataConverter;


    @GetMapping(path = "with_status/{status}/", params = {"page", "limit"})
    public @ResponseBody
    List<LawDto> getAllLawsByStatus(@PathVariable("status") LawStatus status, int page, int limit, @RequestParam(required = false) String userId) {
        List<Law> laws = lawService.getAllLawsByStatus(status, page, limit);

        if (StringUtils.isBlank(userId)) {
            return dataConverter.lawsToDataTransferObjects(laws);
        }
        return dataConverter.lawsToDataTransferObjectsById(laws, userId);
    }

    @GetMapping(path = "with_status/{status}/")
    public @ResponseBody
    List<LawDto> getAllLawsByStatus(@PathVariable("status") LawStatus status, @RequestParam(required = false) String userId) {
        List<Law> laws = lawService.getAllLawsByStatus(status);

        if (StringUtils.isBlank(userId)) {
            return dataConverter.lawsToDataTransferObjects(laws);
        }
        return dataConverter.lawsToDataTransferObjectsById(laws, userId);
    }




    @GetMapping(path = "not_voted")
    public @ResponseBody
    List<LawDto> getAllNotVotedLaws(String userId) {
        return dataConverter.lawsToDataTransferObjectsById(lawService.getAllUnvotedLaws(userId), userId);
    }

    @PostMapping(path = "propose")
    public @ResponseBody
    LawDto propose(@RequestBody LawProposition proposition) throws UnregisteredMemberException {
        return dataConverter.lawToDataTransferObject(lawService.proposeLaw(proposition), proposition.getLegislator());
    }

    @PutMapping(path = "vote")
    public @ResponseBody
    LawDto vote(@RequestBody VoteDto vote) throws UnregisteredMemberException, LawNotUnderVoteException {
        return dataConverter.lawToDataTransferObject(lawService.vote(vote.getLaw(), vote.getMember(), vote.getType(), vote.getReason()), vote.getMember());
    }

    @GetMapping(path = "get")
    public @ResponseBody LawDto getLaw(String lawId, String userId) {
        return dataConverter.lawToDataTransferObject(lawService.get(lawId), userId);
    }

}
