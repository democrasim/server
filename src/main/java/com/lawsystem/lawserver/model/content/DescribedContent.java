package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DescribedContent extends LawContent {
    private String description;
}
