package com.lawsystem.lawserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
public class LawVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    Member voter;

    @JsonIgnore
    @ManyToOne
    Law law;

    @Enumerated(EnumType.ORDINAL)
    VoteType vote;

    @Nullable
    private String reason;
}
