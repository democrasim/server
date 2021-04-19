package com.lawsystem.lawserver.controller;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.repo.LawContentRepository;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "member")
public class MemberController {

    final
    MemberRepository memberRepository;

    final
    LawRepository lawRepository;

    final
    LawContentRepository lawContentRepository;

    public MemberController(MemberRepository userRepository, LawRepository lawRepository, LawContentRepository lawContentRepository) {
        this.memberRepository = userRepository;
        this.lawRepository = lawRepository;
        this.lawContentRepository = lawContentRepository;
    }

    @GetMapping(path = "all")
    public @ResponseBody
    List<Member> allUsers() {
        return memberRepository.findAll();
    }




    @PostMapping(path="request_register")
    public @ResponseBody Law requestRegister(String name, String phone, String reason) {
        Member member = new Member(name, phone);

        memberRepository.save(member);

        Law law = new Law();

        AddMemberContent content = new AddMemberContent();
        content.setMember(member);
        content.setReason(reason);

        law.setLegislator(member);
        law.setContent(content);

        lawContentRepository.save(content);
        lawRepository.save(law);

        return law;
    }
}
