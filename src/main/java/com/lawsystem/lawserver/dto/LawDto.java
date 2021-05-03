package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.LawStatus;
import com.lawsystem.lawserver.model.LawVote;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class LawDto {

    private String id;

    private Member legislator;

    private LawContent content;

    private List<LawVote> votes;

    private LawStatus status = LawStatus.UNDER_VOTE;

    private Date timestamp;

    private boolean constitutional = false;

    private boolean anonymousLegislator = false;

    private String fakeName = "";

    private String contentString;
}
