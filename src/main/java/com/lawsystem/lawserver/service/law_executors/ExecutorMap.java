package com.lawsystem.lawserver.service.law_executors;

import java.util.HashMap;
import java.util.Map;

import com.lawsystem.lawserver.model.content.LawContent;

public class ExecutorMap {
    private Map<Class<? extends LawContent>, LawExecutor<? extends LawContent>> executors;


    public ExecutorMap() {
        executors = new HashMap<>();
    }

    public <T extends LawContent> void put(Class<T> key, LawExecutor<T> value) {
        executors.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T extends LawContent> LawExecutor<T> get(Class<T> clazz) {
        if(executors.containsKey(clazz))
            return (LawExecutor<T>) executors.get(clazz);
        return null;
    }

}
