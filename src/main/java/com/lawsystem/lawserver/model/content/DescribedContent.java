package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Entity
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
