package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class RequirementContent extends DescribedContent {
    public RequirementContent() {
        this.setType(LawType.REQUIREMENT);
    }

    @Nullable
    private Date due;
}
