package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.PunishmentContent;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class ProsecutionDto {
    private String law;
    private int section;
    private String prosecutor;
    private String prosecuted;
    private String info;
}
