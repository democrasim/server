package com.lawsystem.lawserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WhatsAppCode {
    private String code;
    private LocalDateTime expireTime;

    public WhatsAppCode(String code, int expireIn) {
        this(code, LocalDateTime.now().plusSeconds(expireIn));
    }

    public boolean expired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
