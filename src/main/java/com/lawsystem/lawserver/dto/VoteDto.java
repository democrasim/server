package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.VoteType;
import lombok.Data;

@Data
public class VoteDto {
    private long law;
    private long member;
    private VoteType type;
    private String reason;
}
