package com.lawsystem.lawserver.service;

import java.util.Collections;
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
        Member member = memberRepository.findByPhone(phone);

        if(member == null) {
            member = new Member(name, phone);
            memberRepository.save(member);
        } else {
            member.setName(name);
        }

        if(member.isRegistered()) {
            return null;
        }

        LawProposition proposition = new LawProposition();
        proposition.setLegislator(member.getId());
        proposition.setAnonymous(false);
        proposition.setFakeName("");
        proposition.setTitle("Law to add " + member.getName());
        proposition.setContent(Collections.singletonList(new AddMemberContent().setMember(member).setReason(reason)));

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

    public Member byPhone(String phone) {
        return memberRepository.findByPhone(phone);
    }
    public Member byName(String name){
        return memberRepository.findByName(name);
    }
    public void remove(Member member){
        member.setRegistered(false);
        memberRepository.save(member);
    }
}
