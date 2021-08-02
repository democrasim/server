package com.lawsystem.lawserver.service.punishment_executors;

import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.punishments.BanPunishment;
import com.lawsystem.lawserver.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BanPunishmentExecutor implements PunishmentExecutor<BanPunishment> {
    private final MemberService memberService;
    @Override
    public void execute(BanPunishment content, Member member) {
        memberService.remove(member);
    }
}
