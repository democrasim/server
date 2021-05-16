package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.config.WhatsAppConfiguration;
import com.lawsystem.lawserver.dto.WhatsAppCode;
import com.lawsystem.lawserver.dto.wa.SendMessageFormat;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class WhatsAppService {

    RestTemplate restTemplate;

    WhatsAppConfiguration configuration;

    public boolean sendCode(WhatsAppCode code, String phone) {
        ResponseEntity<String> entity = restTemplate.postForEntity(configuration.getServerUid() + "/send_message", new SendMessageFormat(
                "Your access code is :" + code.getCode(),phone + "@c.us"), String.class);
        return entity.getStatusCode().is2xxSuccessful();
    }
}
