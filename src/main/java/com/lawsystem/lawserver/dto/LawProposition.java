package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;

@Data
public class LawProposition {
    private String legislator;
    private boolean anonymous;
    private String fakeName;

    public Law toLaw() {
        Law law = new Law();
        law.setFakeName(fakeName);
        law.setAnonymousLegislator(anonymous);

        return law;
    }

    public LawContent getContent() {
        return new LawContent();
    }
}
