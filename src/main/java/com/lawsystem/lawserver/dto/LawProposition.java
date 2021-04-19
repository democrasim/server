package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;

@Data
public class LawProposition {
    private long legislator;
    private boolean anonymous;
    private boolean constitutional;
    private String fakeName;

    public Law toLaw() {
        Law law = new Law();
        law.setFakeName(fakeName);
        law.setConstitutional(constitutional);
        law.setAnonymousLegislator(anonymous);

        return law;
    }
}
