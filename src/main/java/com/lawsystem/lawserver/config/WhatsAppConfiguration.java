package com.lawsystem.lawserver.config;

import com.google.api.client.util.Value;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:whatsapp.properties")
public class WhatsAppConfiguration {

    @Value("${passedLaws:}")
    private String mainChatId;

    @Value("${councilChat:}")
    private String councilChatId;

    @Value("${server:http://localhost:8081}")
    private String serverUid;
}
