package com.lawsystem.lawserver.controller;

import com.lawsystem.lawserver.dto.ProsecutionDto;
import com.lawsystem.lawserver.exception.InvalidProsecution;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.Prosecution;
import com.lawsystem.lawserver.service.CourtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "court")
@AllArgsConstructor
public class CourtController {
    private CourtService courtService;

    @PostMapping(path = "prosecute")
    public @ResponseBody
    Prosecution prosecute(@RequestBody ProsecutionDto prosecutionDto) throws InvalidProsecution {
        return courtService.prosecute(prosecutionDto);
    }

    @GetMapping(path = "judge")
    public @ResponseBody
    Member judge(){
        return courtService.getJudge();
    }
}
