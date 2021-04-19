package com.lawsystem.lawserver.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class RequirementLawProposition extends LawProposition {
    private String ban;
    private Date due;
}
