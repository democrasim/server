package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.Prosecution;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProsecutionRepository extends MongoRepository<Prosecution,String> {
    Prosecution findByGroupId(String groupId);
}
