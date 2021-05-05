package com.lawsystem.lawserver.service.punishment_executors;

import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.punishments.Punishment;

public interface PunishmentExecutor {
    void execute(Punishment punishment, Member member);
}
