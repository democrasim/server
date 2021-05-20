package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.model.content.ChangeMinMajorityForMemberJoiningContent;
import com.lawsystem.lawserver.model.content.ChangePresidentContent;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MainExecutor {

    private ExecutorMap executorMap;



    public <T extends LawContent> void execute(T content) {
        LawExecutor<T> executor = (LawExecutor<T>) executorMap.get(content.getClass());
        if (executor != null) {
            executor.execute(content);
        }
    }

    public <T extends LawContent> void failed(T content) {
        LawExecutor<T> executor = (LawExecutor<T>) executorMap.get(content.getClass());
        if (executor != null) {
            executor.failed(content);
        }
    }
    public <T extends LawContent> double getMinMajority(Class<T> c) {
        LawExecutor<T> executor = executorMap.get(c);
        if (executor == null) {
            return 0.5;
        }
        return executor.getMinMajority();
    }
}
