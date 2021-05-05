package com.lawsystem.lawserver.model.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RequirementContent extends PunishmentLawContent {
    private String requirement;
    private Date due;
}
