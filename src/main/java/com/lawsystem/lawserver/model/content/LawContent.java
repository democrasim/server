package com.lawsystem.lawserver.model.content;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddMemberContent.class, name = "ADD_MEMBER"),
        @JsonSubTypes.Type(value = FactContent.class, name = "FACT")
})
public class LawContent {
}
