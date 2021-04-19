package com.lawsystem.lawserver.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BanLawProposition extends LawProposition {
    private String ban;
}
