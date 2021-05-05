package com.lawsystem.lawserver.controller;

import com.lawsystem.lawserver.exception.DeadlineUnmetException;
import com.lawsystem.lawserver.exception.LawNotPassesException;
import com.lawsystem.lawserver.exception.UnenforceableLawException;
import com.lawsystem.lawserver.exception.UnregisteredMemberException;
import com.lawsystem.lawserver.service.CourtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "court")
@AllArgsConstructor
public class CourtController {
    private CourtService courtService;
    @PostMapping(path = "prosecute")
    public void prosecute(String law, String prosecutorId, String prosecutedId, String reason) throws LawNotPassesException, UnregisteredMemberException, DeadlineUnmetException, UnenforceableLawException {
        courtService.prosecute(law,prosecutorId,prosecutedId,reason);
    }
    @PostMapping(path = "response")
    public void judgeReply(String prosecutionId, boolean accepted){
        courtService.judgeReply(prosecutionId,accepted);
    }
}
