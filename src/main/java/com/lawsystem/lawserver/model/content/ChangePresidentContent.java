package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.Member;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class ChangePresidentContent extends LawContent{
    @DBRef
    private Member newPresident;
    private String reason;
}
