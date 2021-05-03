package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import org.springframework.data.mongodb.core.mapping.Document;


public class FactContent extends DescribedContent {
    public FactContent() {
        this.setType(LawType.FACT);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
