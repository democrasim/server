package com.lawsystem.lawserver.model.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RequirementContent extends DescribedContent {
    private Date due;
    @Override
    public String toString() {
        return super.toString();
    }
}
