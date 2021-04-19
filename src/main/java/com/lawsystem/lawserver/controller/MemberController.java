package com.lawsystem.lawserver.controller;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.repo.LawContentRepository;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "member")
public class MemberController {

    final MemberService memberService;


    @GetMapping(path = "all")
    public @ResponseBody List<Member> allUsers() {
        return memberService.allUsers();
    }

    @GetMapping(path = "all", params = {"registered"})
    public @ResponseBody List<Member> allUsers(boolean registered) {
        return memberService.allUsers(registered);
    }

    @PostMapping(path="request_register")
    public @ResponseBody Law requestRegister(String name, String phone, String reason) {
        return memberService.requestRegister(name, phone, reason);
    }

    
}
