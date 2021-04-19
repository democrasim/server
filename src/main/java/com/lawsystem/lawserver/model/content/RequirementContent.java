package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
public class RequirementContent extends DescribedContent {
    public RequirementContent() {
        this.setType(LawType.REQUIREMENT);
    }
    private Date due;
}
