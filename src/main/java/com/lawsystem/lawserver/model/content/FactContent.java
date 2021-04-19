package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;

import javax.persistence.Entity;


@Entity
public class FactContent extends DescribedContent {
    public FactContent() {
        this.setType(LawType.FACT);
    }
}
