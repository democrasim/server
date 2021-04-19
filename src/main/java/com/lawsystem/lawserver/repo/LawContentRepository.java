package com.lawsystem.lawserver.repo;

import com.lawsystem.lawserver.model.content.LawContent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LawContentRepository extends PagingAndSortingRepository<LawContent, Long> {
}
