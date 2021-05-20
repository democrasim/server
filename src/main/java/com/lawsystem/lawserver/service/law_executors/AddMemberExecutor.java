package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.repo.ConfigurationLawsRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.service.WhatsAppService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddMemberExecutor implements LawExecutor<AddMemberContent> {

    private final WhatsAppService whatsAppService;
    private final MemberRepository memberRepository;
    private final ConfigurationLawsRepository configurationLawsRepository;
    @Override
    public void execute(AddMemberContent content) {

        Member member = content.getMember();
        member.setRegistered(true);
        memberRepository.save(member);

        whatsAppService.sendRegistered(member);
    }

    @Override
    public void failed(AddMemberContent content) {
        whatsAppService.sendFailedRegister(content.getMember());
    }

    @Override
    public double getMinMajority() {
        return configurationLawsRepository.findAll().get(0).getMinMajorityForMemberJoining();
    }
}
