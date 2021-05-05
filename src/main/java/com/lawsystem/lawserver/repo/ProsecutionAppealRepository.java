package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.ProsecutionAppeal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProsecutionAppealRepository extends MongoRepository<ProsecutionAppeal, String> {
}
