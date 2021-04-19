package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import com.lawsystem.lawserver.model.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
public class AddMemberContent extends LawContent {
    public AddMemberContent() {
        this.setType(LawType.ADD_MEMBER);
    }

    @OneToOne
    private Member member;

    private String reason;
}
