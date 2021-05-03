package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfiguationLawsRepository extends MongoRepository<ConfigurationLaws, String> {
}
