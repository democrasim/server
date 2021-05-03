package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.VoteType;
import lombok.Data;

@Data
public class VoteDto {
    private String law;
    private String member;
    private VoteType type;
    private String reason;
}
