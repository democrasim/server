package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.content.punishments.Punishment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProhibitionContent extends PunishmentLawContent{
    private String prohibition;
}