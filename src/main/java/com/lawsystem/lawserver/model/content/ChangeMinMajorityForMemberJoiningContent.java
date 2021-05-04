package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChangeMinMajorityForMemberJoiningContent extends LawContent {
    private double min;

    public ChangeMinMajorityForMemberJoiningContent() {
        this.setType(LawType.CHANGE_MIN_MAJORITY_FOR_MEMBER_JOINING);
    }
}
