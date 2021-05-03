package com.lawsystem.lawserver.model.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Accessors(chain = true)
public class LawContent {

    private LawType type;

    @Override
    public String toString() {
        return "";
    }
}
