package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.config.WhatsAppConfiguration;
import com.lawsystem.lawserver.dto.WhatsAppCode;
import com.lawsystem.lawserver.dto.wa.SendMessageFormat;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class WhatsAppService {

    RestTemplate restTemplate;

    WhatsAppConfiguration configuration;

    public boolean sendCode(WhatsAppCode code, String phone) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getServerUid(), new SendMessageFormat(
                "Your access code is :" + code.getCode(),phone + "@c.us"), Void.class);
        return entity.getStatusCode().is2xxSuccessful();
    }
}
