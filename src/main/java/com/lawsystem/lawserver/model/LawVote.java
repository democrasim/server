package com.lawsystem.lawserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;


@Data
@Document
@Accessors(chain = true)
public class LawVote {
    @Id
    private String id;

    @DBRef
    Member voter;

    @DBRef
    @JsonIgnore
    Law law;

    VoteType vote;

    @Nullable
    private String reason;
}
