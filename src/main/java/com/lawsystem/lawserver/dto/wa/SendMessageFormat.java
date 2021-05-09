package com.lawsystem.lawserver.dto.wa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageFormat {
    private String message;
    private String chatId;
}
