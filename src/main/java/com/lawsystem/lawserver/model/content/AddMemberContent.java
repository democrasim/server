package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import com.lawsystem.lawserver.model.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Accessors(chain = true)
public class AddMemberContent extends LawContent {
    public AddMemberContent() {
        this.setType(LawType.ADD_MEMBER);
    }

    private Member member;

    private String reason;

    @Override
    public String toString() {
        return "Add new member: " + member.getName();
    }
}
