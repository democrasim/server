package com.lawsystem.lawserver.model;

import com.lawsystem.lawserver.model.content.PunishmentLawContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Prosecution {
    @Id
    private String id;
    @DBRef
    private Law punishmentLawContent;
    @DBRef
    private Member prosecuted;
    @DBRef
    private Member prosecutor;
    private String reason;
    private boolean accepted;
}
