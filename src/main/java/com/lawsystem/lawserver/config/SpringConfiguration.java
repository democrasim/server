package com.lawsystem.lawserver.config;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.service.VariableService;
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
    private final VariableService variableService;
    private MemberRepository memberRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        if (!variableService.hasInstance()) {
            ConfigurationLaws laws = new ConfigurationLaws();
            laws.setMinMajorityForMemberJoining(.5);
            laws.setPresident(null);
            laws.setMainJudge(memberRepository.findByName("דן"));
            laws.setTimeForLawsToPass(10000);

            variableService.save(laws);
        }
    }
}
