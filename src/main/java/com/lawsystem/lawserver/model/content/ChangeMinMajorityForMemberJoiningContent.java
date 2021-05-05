package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChangeMinMajorityForMemberJoiningContent extends LawContent {
    private double min;

}
