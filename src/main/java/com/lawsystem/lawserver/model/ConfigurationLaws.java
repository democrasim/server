package com.lawsystem.lawserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class ConfigurationLaws {
    private Member president;
    private int TimeForLawsToPass;
}
