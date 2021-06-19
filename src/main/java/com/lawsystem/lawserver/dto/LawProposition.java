package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;

import java.util.List;

@Data
public class LawProposition {
    private String legislator;
    private String title;
    private boolean anonymous;
    private String fakeName;
    private List<LawContent> content;

    public Law toLaw() {
        Law law = new Law();
        law.setFakeName(fakeName);
        law.setAnonymousLegislator(anonymous);

        return law;
    }
}
