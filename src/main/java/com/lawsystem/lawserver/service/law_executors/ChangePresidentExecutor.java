package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.model.content.ChangePresidentContent;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.repo.ConfigurationLawsRepository;

public class ChangePresidentExecutor implements LawExecutor {
    private ConfigurationLawsRepository configuationLawsRepository;

    @Override
    public void execute(LawContent content) {
        //configuationLawsRepository.save(new ConfigurationLaws(new Member("gil","5"), 24));
        ConfigurationLaws configurationLaws = configuationLawsRepository.findAll().get(0);
        configurationLaws.setPresident(((ChangePresidentContent) content).getNewPresident());
        configuationLawsRepository.save(configurationLaws);
    }
}
