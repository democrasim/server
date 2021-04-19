package com.lawsystem.lawserver.dto;

import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddMemberProposition extends LawProposition {
    Member member;
    String reason;

    @Override
    public LawContent getContent() {
        return new AddMemberContent().setMember(member).setReason(reason);
    }
}
