package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@Accessors(chain = true)
public class RequirementContent extends DescribedContent {
    private Date due;
    @Override
    public String toString() {
        return super.toString();
    }
}
