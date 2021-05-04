package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.content.AddMemberContent;
import com.lawsystem.lawserver.model.content.ChangePresidentContent;
import com.lawsystem.lawserver.model.content.LawContent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MainExecutor implements LawExecutor {
    private AddMemberExecutor addMemberExecutor;
    private ChangePresidentExecutor changePresidentExecutor;
    private Map<Class, LawExecutor> executors;

    private void initMap() {
        executors = new HashMap<Class, LawExecutor>() {{
            put(ChangePresidentContent.class, changePresidentExecutor);
            put(AddMemberContent.class, addMemberExecutor);
        }};
    }

    @Override
    public void execute(LawContent content) {
        LawExecutor executor = executors.get(content.getClass());
        if (executor != null) {
            executor.execute(content);
        }
    }
}
