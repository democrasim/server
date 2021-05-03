package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.Member;
import lombok.Data;

@Data
public class ChangePresidentContent extends LawContent{
    private Member newPresident;
    private String reason;
}
