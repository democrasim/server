package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.dto.AddMemberProposition;
import com.lawsystem.lawserver.dto.LawProposition;
import com.lawsystem.lawserver.exception.UnregisteredMemberException;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.repo.LawContentRepository;
import com.lawsystem.lawserver.repo.LawRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final LawService lawService;

    @SneakyThrows
    public Law requestRegister(String name, String phone, String reason) {
        Member member = new Member(name, phone);

        memberRepository.save(member);

        AddMemberProposition proposition = new AddMemberProposition();
        proposition.setLegislator(member.getId());
        proposition.setMember(member);
        proposition.setReason(reason);
        proposition.setAnonymous(false);
        proposition.setConstitutional(false);
        proposition.setFakeName("");

        return lawService.proposeLaw(proposition, false);
    }



    public List<Member> allUsers(boolean registered) {
        return memberRepository.findAllByRegistered(registered);
    }

    public List<Member> allUsers() {
        return memberRepository.findAll();
    }
}
