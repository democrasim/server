package com.lawsystem.lawserver.service.punishment_executors;

import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.content.LawContent;
import com.lawsystem.lawserver.model.punishments.BanPunishment;
import com.lawsystem.lawserver.model.punishments.Punishment;
import com.lawsystem.lawserver.service.law_executors.LawExecutor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MainPunishmentExecutor {
    private Map<Class<? extends Punishment>, PunishmentExecutor<? extends Punishment>> executors;
    private BanPunishmentExecutor banPunishmentExecutor;

    @EventListener(ApplicationReadyEvent.class)
    private void init(){
        executors = new HashMap<Class<? extends Punishment>, PunishmentExecutor<? extends Punishment>>() {{
            put(BanPunishment.class, banPunishmentExecutor);
        }};
    }

    public void execute(Punishment punishment, Member member) {
        ((PunishmentExecutor<Punishment>) executors.get(punishment.getClass())).execute(punishment, member);
    }

    public void undo(Punishment punishment, Member member) {
        ((PunishmentExecutor<Punishment>) executors.get(punishment.getClass())).undo(punishment, member);
    }
}
