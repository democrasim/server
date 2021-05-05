package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.content.punishments.Punishment;
import lombok.Data;

@Data
public class PunishmentLawContent extends LawContent {
    protected Punishment punishment;
}
