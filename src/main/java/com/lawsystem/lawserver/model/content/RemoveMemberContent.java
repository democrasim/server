package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.Member;
import lombok.Data;

@Data
public class RemoveMemberContent extends DescribedContent{
    private Member member;
}
