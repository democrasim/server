package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ConfigurationLawsRepository extends MongoRepository<ConfigurationLaws, String> {

    @Query("{}")
    ConfigurationLaws findOne();
}
