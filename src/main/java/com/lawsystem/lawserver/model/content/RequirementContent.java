package com.lawsystem.lawserver.model.content;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RequirementContent extends DescribedContent {
    private String due;

    @Override
    public String toString() {
        return super.toString();
    }
}
