package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.model.content.ChangeMinMajorityForMemberJoiningContent;
import com.lawsystem.lawserver.service.VariableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChangeMinMajorityForMemberJoiningExecutor implements LawExecutor<ChangeMinMajorityForMemberJoiningContent> {
    private final VariableService variableService;

    @Override
    public void execute(ChangeMinMajorityForMemberJoiningContent content) {
        ConfigurationLaws configurationLaws = variableService.getInstance();
        configurationLaws.setMinMajorityForMemberJoining(content.getMin());
        variableService.save(configurationLaws);
    }

    @Override
    public void failed(ChangeMinMajorityForMemberJoiningContent content) {

    }

    @Override
    public double getMinMajority() {
        return 1;
    }
}
