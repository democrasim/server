package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.content.LawContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LawContentRepository extends MongoRepository<LawContent, String> {
}
