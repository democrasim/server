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
        @JsonSubTypes.Type(value = FactContent.class, name = "FACT"),
        @JsonSubTypes.Type(value = BanContent.class, name="BAN"),
        @JsonSubTypes.Type(value = ChangeMinMajorityForMemberJoiningContent.class, name="CHANGE_MIN_MAJ_ADD_MEMBER"),
        @JsonSubTypes.Type(value = ChangePresidentContent.class, name="CHANGE_PRESIDENT"),
        @JsonSubTypes.Type(value = EventContent.class, name="EVENT"),
        @JsonSubTypes.Type(value = RequirementContent.class, name="REQUIREMENT"),
        @JsonSubTypes.Type(value =RemoveMemberContent.class, name="REMOVE_MEMBER")
})
public class LawContent {
}
