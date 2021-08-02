package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.punishments.Punishment;
import lombok.Data;

@Data
public class PunishmentContent extends DescribedContent{
    private Punishment punishment;
}
