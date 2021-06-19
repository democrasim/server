package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;


@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AddMemberContent extends LawContent {

    @DBRef
    private Member member;

    private String reason;

    @Override
    public String toString() {
        return member.getName() + " with phone number " + member.getPhone() + " will be added to Democrasim";
    }
}
