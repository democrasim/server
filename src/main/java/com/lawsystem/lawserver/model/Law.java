package com.lawsystem.lawserver.model;

import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Data
@Document
@Accessors(chain = true)
public class Law {

    @Id
    private String id;

    private Member legislator;

    private LawContent content;

    private List<LawVote> votes;

    private LawStatus status = LawStatus.UNDER_VOTE;

    @CreatedDate
    private Date timestamp = new Date();

    private boolean constitutional = false;

    private boolean anonymousLegislator = false;

    private String fakeName = "";

}
