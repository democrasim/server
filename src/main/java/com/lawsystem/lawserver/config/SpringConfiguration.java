package com.lawsystem.lawserver.config;

import java.util.List;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.repo.ConfigurationLawsRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Configuration
public class SpringConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    ConfigurationLawsRepository configurationLawsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        List<ConfigurationLaws> list = configurationLawsRepository.findAll();
        if(list.isEmpty()) {
            ConfigurationLaws laws = new ConfigurationLaws();
            laws.setMinMajorityForMemberJoining(.5);
            laws.setPresident(null);
            laws.setTimeForLawsToPass(10000);

            configurationLawsRepository.save(laws);
        }
    }
}
