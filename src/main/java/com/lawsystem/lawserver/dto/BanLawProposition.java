package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.content.BanContent;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BanLawProposition extends LawProposition {
    private String ban;

    @Override
    public LawContent getContent() {
        return new BanContent().setDescription(ban);
    }
}
