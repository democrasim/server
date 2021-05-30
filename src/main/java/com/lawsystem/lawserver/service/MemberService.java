package com.lawsystem.lawserver.service;

import java.util.List;

import com.lawsystem.lawserver.dto.LawProposition;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.repo.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final LawService lawService;

    @SneakyThrows
    public Law requestRegister(String name, String phone, String reason) {
        Member member = new Member(name, phone);

        memberRepository.save(member);

        LawProposition proposition = new LawProposition();
        proposition.setLegislator(member.getId());
        proposition.setAnonymous(false);
        proposition.setFakeName("");
        proposition.setContent(new AddMemberContent().setMember(member).setReason(reason));

        return lawService.proposeLaw(proposition);
    }



    public List<Member> allUsers(boolean registered) {
        return memberRepository.findAllByRegistered(registered);
    }

    public List<Member> allUsers() {
        return memberRepository.findAll();
    }

    public Member byId(String id) {
        return memberRepository.findById(id).orElse(null);
    }
}
