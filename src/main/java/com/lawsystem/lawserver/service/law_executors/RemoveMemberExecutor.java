package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.content.RemoveMemberContent;
import com.lawsystem.lawserver.repo.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveMemberExecutor implements LawExecutor<RemoveMemberContent> {
    private final MemberRepository memberRepository;

    @Override
    public void execute(RemoveMemberContent content) {
        memberRepository.delete(content.getMember());
    }
}
