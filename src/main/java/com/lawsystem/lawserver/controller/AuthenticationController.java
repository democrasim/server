package com.lawsystem.lawserver.controller;

import com.lawsystem.lawserver.dto.WhatsAppCode;
import com.lawsystem.lawserver.service.WhatsAppService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    WhatsAppService whatsAppService;

    @GetMapping("/code/wa")
    public void createWhatsappCode(HttpServletRequest request, HttpServletResponse response, String mobile) {
        WhatsAppCode code = generateCode();

        whatsAppService.sendCode(code, mobile);
    }

    private WhatsAppCode generateCode() {
        String code = RandomStringUtils.randomNumeric(6);

        return new WhatsAppCode(code, 120);
    }
}
