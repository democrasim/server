package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChangePresidentContent extends LawContent{
    @DBRef
    private Member newPresident;
    private String reason;
}
