package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.Prosecution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProsecutionRepository extends MongoRepository<Prosecution, String> {
}
