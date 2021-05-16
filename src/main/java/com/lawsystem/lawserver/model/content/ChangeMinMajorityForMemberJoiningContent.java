package com.lawsystem.lawserver.model.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ChangeMinMajorityForMemberJoiningContent extends LawContent {
    private double min;

}
