package com.lawsystem.lawserver.service;

import com.lawsystem.lawserver.config.WhatsAppConfiguration;
import com.lawsystem.lawserver.dto.LawDto;
import com.lawsystem.lawserver.dto.WhatsAppCode;
import com.lawsystem.lawserver.dto.wa.SendMessageFormat;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class WhatsAppService {

    RestTemplate restTemplate;

    WhatsAppConfiguration configuration;

    public boolean sendRegistered(Member member) {
        ResponseEntity<String> entity = restTemplate.postForEntity(configuration.getServerUid() + "/send_message", new SendMessageFormat(
                "🏆 You are now successfully registered to Democrasim!", member.getPhone() + "@c.us"), String.class);
        return entity.getStatusCode().is2xxSuccessful();
    }

    public boolean sendFailedRegister(Member member) {
        ResponseEntity<String> entity = restTemplate.postForEntity(configuration.getServerUid() + "/send_message", new SendMessageFormat(
                "⛔ Unfortunately, the law to add you to Democrasim failed.", member.getPhone() + "@c.us"), String.class);
        return entity.getStatusCode().is2xxSuccessful();
    }

    public boolean sendCode(WhatsAppCode code, String phone) {
        ResponseEntity<String> entity = restTemplate.postForEntity(configuration.getServerUid() + "/send_message", new SendMessageFormat(
                "Your access code for 🛂 Democrasim is\n"
                        + code.getCode()
                        + "\n\n_if this was not you please ignore this._", phone + "@c.us"), String.class);
        return entity.getStatusCode().is2xxSuccessful();
    }

    public boolean sendFinishedLaw(LawDto law) {
        ResponseEntity<String> entity = restTemplate.postForEntity(configuration.getServerUid() + "/send_message", new SendMessageFormat(
                "*Law #" + law.getNumber() + "*\n"
                        + "*Legislator:* " + law.getLegislator().getName() + "\n\n"
                        + law.getContentString() + "\n"
                        + "*Status:* " + law.getStatus(),
                configuration.getMainChatId()), String.class
        );

        return entity.getStatusCode().is2xxSuccessful();
    }
}
