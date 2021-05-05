package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.model.content.ChangeMinMajorityForMemberJoiningContent;
import com.lawsystem.lawserver.model.content.ChangePresidentContent;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MainExecutor {
    private AddMemberExecutor addMemberExecutor;
    private ChangePresidentExecutor changePresidentExecutor;
    private ChangeMinMajorityForMemberJoiningExecutor changeMinMajorityForMemberJoiningExecutor;
    private Map<Class, LawExecutor> executors;

    @PostConstruct
    private void initMap() {
        executors = new HashMap<Class, LawExecutor>() {{
            put(ChangePresidentContent.class, changePresidentExecutor);
            put(AddMemberContent.class, addMemberExecutor);
            put(ChangeMinMajorityForMemberJoiningContent.class, changeMinMajorityForMemberJoiningExecutor);
        }};
    }

    public void execute(LawContent content) {
        LawExecutor executor = executors.get(content.getClass());
        if (executor != null) {
            executor.execute(content);
        }
    }

    public double getMinMajority(Class c) {
        LawExecutor executor = executors.get(c);
        if (executor == null) {
            return 0.5;
        }
        return executor.getMinMajority();
    }
}
