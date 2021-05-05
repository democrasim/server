package com.lawsystem.lawserver.service.punishment_executors;

import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.punishments.BanPunishment;
import com.lawsystem.lawserver.model.content.punishments.Punishment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MainPunishmentExecutor {
    private BanExecutor banExecutor;
    private Map<Class, PunishmentExecutor> executors;
    @PostConstruct
    private void initMap() {
        executors = new HashMap<Class, PunishmentExecutor>() {{
            put(BanPunishment.class, banExecutor);
        }};
    }

    public void execute(Punishment punishment, Member member) {
        PunishmentExecutor executor = executors.get(punishment.getClass());
        if (executor != null) {
            executor.execute(punishment, member);
        }
    }
}
