package com.lawsystem.lawserver.model.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class DescribedContent extends LawContent {
    private String description;

    @Override
    public String toString() {
        return description;
    }
}
