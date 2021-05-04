package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.repo.ConfigurationLawsRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberExecutor implements LawExecutor{
    @Autowired
    private MemberRepository memberRepository;
    private ConfigurationLawsRepository configurationLawsRepository;
    @Override
    public void execute(LawContent content) {
        ((AddMemberContent)content).getMember().setRegistered(true);
        memberRepository.save(((AddMemberContent)content).getMember());
        //implement: send to whatsapp api
    }

    @Override
    public double getMinMajority() {
        return configurationLawsRepository.findAll().get(0).getMinMajorityForMemberJoining();
    }
}
