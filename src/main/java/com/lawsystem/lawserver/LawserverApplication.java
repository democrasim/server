package com.lawsystem.lawserver;

import com.lawsystem.lawserver.model.ConfigurationLaws;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.repo.ConfigurationLawsRepository;
import com.lawsystem.lawserver.repo.MemberRepository;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@SpringBootApplication
public class LawserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawserverApplication.class, args);
    }

}

@Component
@AllArgsConstructor
class CommandLineAppStartupRunner implements CommandLineRunner {
    private MemberRepository memberRepository;
    private ConfigurationLawsRepository configurationLawsRepository;
    @Override
    public void run(String...args) throws Exception {
        ConfigurationLaws configurationLaws=new ConfigurationLaws(memberRepository.findById("608fe48f36e4a622e8a59c16").get(),24);
        configurationLawsRepository.save(configurationLaws);
    }
}