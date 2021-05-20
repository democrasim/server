package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.model.content.ChangePresidentContent;
import com.lawsystem.lawserver.repo.ConfigurationLawsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ChangePresidentExecutor implements LawExecutor<ChangePresidentContent> {
    private ConfigurationLawsRepository configurationLawsRepository;

    @Override
    public void execute(ChangePresidentContent content) {
        //configurationLawsRepository.save(new ConfigurationLaws(new Member("gil","5"), 24));
        ConfigurationLaws configurationLaws = configurationLawsRepository.findAll().get(0);
        configurationLaws.setPresident(content.getNewPresident());
        configurationLawsRepository.save(configurationLaws);
    }

    @Override
    public void failed(ChangePresidentContent content) {

    }

    @Override
    public double getMinMajority() {
        return 0.7;
    }
}
