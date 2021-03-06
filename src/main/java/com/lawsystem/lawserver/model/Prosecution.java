package com.lawsystem.lawserver.model;

import com.lawsystem.lawserver.model.content.PunishmentContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class Prosecution {
    @Id
    private String id;
    @DBRef
    private Law law;
    private int section;
    private PunishmentContent punishmentContent;
    @DBRef
    private Member prosecutor;
    @DBRef
    private Member prosecuted;
    private String info;
    private ProsecutionStatus status;
    private boolean isAppealed;
    private String groupId;
}
