package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.content.ChangeMinMajorityForMemberJoiningContent;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;

@Data
public class ChangeMinMajorityForMemberJoiningProposition extends LawProposition{
    private double min;
    @Override
    public LawContent getContent(){
        return new ChangeMinMajorityForMemberJoiningContent().setMin(min);
    }
}
