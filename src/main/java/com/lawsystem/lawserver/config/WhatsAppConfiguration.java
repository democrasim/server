package com.lawsystem.lawserver.config;

import javax.annotation.PostConstruct;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:whats_app.properties")
public class WhatsAppConfiguration {

    @Value("${passedLaws:}")
    private String mainChatId;
    @Value("${councilChat:}")
    private String councilChatId;
    @Value("${server:http://localhost:8081}")
    private String serverUid;
    @Value("${apiKey:}")
    private String apiKey;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("Main Chat: " + mainChatId);
        System.out.println("Council Chat: " + councilChatId);
        System.out.println("Server: " + serverUid);
    }
}
