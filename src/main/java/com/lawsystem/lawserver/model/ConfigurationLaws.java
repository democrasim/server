package com.lawsystem.lawserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class ConfigurationLaws {
    @DBRef
    private Member president;
    private int TimeForLawsToPass;
}
