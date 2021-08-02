package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.content.RemoveMemberContent;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveMemberExecutor implements LawExecutor<RemoveMemberContent> {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Override
    public void execute(RemoveMemberContent content) {
        memberService.remove(content.getMember());
    }
}
