package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Accessors(chain = true)
@Data
public class BanContent extends DescribedContent {
    public BanContent() {
        this.setType(LawType.BAN);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
