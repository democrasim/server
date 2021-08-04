package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.config.WhatsAppConfiguration;
import com.lawsystem.lawserver.dto.WhatsAppCode;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.model.Prosecution;
import com.lawsystem.lawserver.util.DataConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@AllArgsConstructor
@Service
public class WhatsAppService {

    RestTemplate restTemplate;
    DataConverter dataConverter;
    WhatsAppConfiguration configuration;

    public boolean sendRegistered(Member member) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getServerUid() + "/add_member_passed", member, Void.class);
        return entity.getStatusCode().is2xxSuccessful();
    }

    public boolean sendFailedRegister(Member member) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getServerUid() + "/add_member_failed", member, Void.class);
        return entity.getStatusCode().is2xxSuccessful();
    }

    public boolean sendCode(WhatsAppCode code, String phone) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getServerUid() + "/send_code",
                Arrays.asList(code.getCode(), phone + "@c.us"), Void.class);
        return entity.getStatusCode().is2xxSuccessful();
    }


    public boolean sendNewLaw(Law law) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getServerUid() + "/new_law", dataConverter.lawToDataTransferObject(law), Void.class);
        return entity.getStatusCode().is2xxSuccessful();
    }

    public boolean sendFinishedLaw(Law law) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getServerUid() + "/finished_law", dataConverter.lawToDataTransferObject(law), Void.class);
        return entity.getStatusCode().is2xxSuccessful();
    }
    public String sendProsecution(Prosecution prosecution){
        return restTemplate.postForEntity(configuration.getServerUid()+"/new_prosecution",prosecution,String.class).getBody();
    }
    public void sendProsecutionDecided(Prosecution prosecution){
        restTemplate.postForEntity(configuration.getServerUid()+"/prosecution_decided",prosecution,Void.class);
    }
    public void sendProsecutionAppealed(Prosecution prosecution){
        restTemplate.postForEntity(configuration.getServerUid()+"/prosecution_appealed",prosecution,Void.class);
    }
    public void sendProsecutionAppealedDecided(Prosecution prosecution){
        restTemplate.postForEntity(configuration.getServerUid()+"/prosecution_appeal_decided",prosecution,Void.class);
    }
}
