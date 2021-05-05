package com.lawsystem.lawserver.service.punishment_executors;

import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.punishments.Punishment;
import com.lawsystem.lawserver.repo.MemberRepository;

public class BanExecutor implements PunishmentExecutor{
    private MemberRepository memberRepository;
    @Override
    public void execute(Punishment punishment, Member member) {
        memberRepository.delete(member);
    }
}
