package com.lawsystem.lawserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@Accessors(chain = true)
public class Law {

    @Transient
    public static final String SEQUENCE_NAME = "law_sequence";

    @Id
    private String id;

    private long number;

    @DBRef
    private Member legislator;

    private String title;
    private List<LawContent> content;

    @DBRef
    private List<LawVote> votes = new ArrayList<>();

    private LawStatus status = LawStatus.UNDER_VOTE;

    @CreatedDate
    private Date timestamp = new Date();

    private Date resolveTime;

    private boolean anonymousLegislator = false;

    private String fakeName = "";

}
