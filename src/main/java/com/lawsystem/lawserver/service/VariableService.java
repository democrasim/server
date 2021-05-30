package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.repo.ConfigurationLawsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VariableService {

    private final ConfigurationLawsRepository repository;

    public ConfigurationLaws getInstance() {
        return repository.findAll().get(0);
    }

    public void save(ConfigurationLaws laws) {
        repository.save(laws);
    }

    public boolean hasInstance() {
        return repository.findAll().stream().findAny().isPresent();
    }

}
