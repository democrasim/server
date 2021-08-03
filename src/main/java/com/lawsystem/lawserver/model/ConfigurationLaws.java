package com.lawsystem.lawserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ConfigurationLaws {
    @Id
    private String id;
    @DBRef
    private Member president;
    @DBRef
    private Member mainJudge;
    private int timeForLawsToPass;
    private double minMajorityForMemberJoining;
}
