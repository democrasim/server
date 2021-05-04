package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.content.LawContent;

public interface LawExecutor {
    void execute(LawContent content);
    double getMinMajority();
}
