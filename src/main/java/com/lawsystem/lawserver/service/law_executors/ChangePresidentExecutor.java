package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.model.content.ChangePresidentContent;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ChangePresidentExecutor implements LawExecutor {
    private ConfigurationLawsRepository configurationLawsRepository;

    @Override
    public void execute(LawContent content) {
        //configuationLawsRepository.save(new ConfigurationLaws(new Member("gil","5"), 24));
        ConfigurationLaws configurationLaws = configurationLawsRepository.findAll().get(0);
        configurationLaws.setPresident(((ChangePresidentContent) content).getNewPresident());
        configurationLawsRepository.save(configurationLaws);
    }

    @Override
    public double getMinMajority() {
        return 0.7;
    }
}
