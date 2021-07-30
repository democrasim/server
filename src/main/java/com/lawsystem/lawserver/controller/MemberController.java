package com.lawsystem.lawserver.controller;

import java.util.List;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "member")
public class MemberController {

    final MemberService memberService;


    @GetMapping(path = "all")
    public @ResponseBody
    List<Member> allUsers() {
        return memberService.allUsers();
    }

    @GetMapping(path = "all", params = {"registered"})
    public @ResponseBody
    List<Member> allUsers(boolean registered) {
        return memberService.allUsers(registered);
    }

    @PostMapping(path = "request_register")
    public @ResponseBody
    Law requestRegister(String name, String phone, String reason) {
        return memberService.requestRegister(name, phone, reason);
    }

    @GetMapping(path = "by_id/{id}")
    public @ResponseBody
    Member byId(@PathVariable("id") String id) {
        return memberService.byId(id);
    }

    @GetMapping(path = "by_phone/{phone}")
    public @ResponseBody
    Member byPhone(@PathVariable("phone") String phone) {
        return memberService.byPhone(phone);
    }


}
