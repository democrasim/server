package com.lawsystem.lawserver.service.punishment_executors;

import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.punishments.Punishment;

public interface PunishmentExecutor<T extends Punishment> {
    default void execute(T content, Member member){}
}
