package com.lawsystem.lawserver.config;

import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.model.content.ChangeMinMajorityForMemberJoiningContent;
import com.lawsystem.lawserver.model.content.ChangePresidentContent;
import com.lawsystem.lawserver.model.content.RemoveMemberContent;
import com.lawsystem.lawserver.service.law_executors.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ExecutorConfiguration {
    private AddMemberExecutor addMemberExecutor;
    private ChangePresidentExecutor changePresidentExecutor;
    private ChangeMinMajorityForMemberJoiningExecutor changeMinMajorityForMemberJoiningExecutor;
    private RemoveMemberExecutor removeMemberExecutor;

    @Bean
    public ExecutorMap executorMap() {
        return new ExecutorMap() {{
            put(ChangePresidentContent.class, changePresidentExecutor);
            put(AddMemberContent.class, addMemberExecutor);
            put(ChangeMinMajorityForMemberJoiningContent.class, changeMinMajorityForMemberJoiningExecutor);
            put(RemoveMemberContent.class, removeMemberExecutor);
        }};
    }
}
