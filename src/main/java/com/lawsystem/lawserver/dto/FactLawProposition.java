package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.content.FactContent;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FactLawProposition extends LawProposition {
    private String fact;

    @Override
    public LawContent getContent() {
        return new FactContent().setDescription(fact);
    }
}
