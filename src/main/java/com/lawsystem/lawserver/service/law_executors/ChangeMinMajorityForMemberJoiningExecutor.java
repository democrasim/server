package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.model.content.ChangeMinMajorityForMemberJoiningContent;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.repo.ConfigurationLawsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChangeMinMajorityForMemberJoiningExecutor implements LawExecutor<ChangeMinMajorityForMemberJoiningContent> {
    private ConfigurationLawsRepository configurationLawsRepository;
    @Override
    public void execute(ChangeMinMajorityForMemberJoiningContent content) {
        ConfigurationLaws configurationLaws=configurationLawsRepository.findAll().get(0);
        configurationLaws.setMinMajorityForMemberJoining(content.getMin());
        configurationLawsRepository.save(configurationLaws);
    }

    @Override
    public void failed(ChangeMinMajorityForMemberJoiningContent content) {

    }

    @Override
    public double getMinMajority() {
        return 1;
    }
}
