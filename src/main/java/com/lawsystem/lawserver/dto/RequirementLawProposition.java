package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.content.FactContent;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.model.content.RequirementContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class RequirementLawProposition extends LawProposition {
    private String requirement;
    private Date due;

    @Override
    public LawContent getContent() {
        return new RequirementContent().setDue(due).setDescription(requirement);
    }
}
