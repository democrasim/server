package com.lawsystem.lawserver.service.law_executors;

import com.lawsystem.lawserver.model.content.LawContent;

public interface LawExecutor<T extends LawContent> {
    default void execute(T content) {

    }

    default void failed(T content) {

    }

    default double getMinMajority() {
        return 0.5;
    }
}
