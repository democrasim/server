package com.lawsystem.lawserver.model.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawType;
import com.lawsystem.lawserver.model.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class AddMemberContent extends LawContent {
    public AddMemberContent() {
        this.setType(LawType.ADD_MEMBER);
    }

    @OneToOne
    private Member member;

    private String reason;
}
