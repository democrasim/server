package com.lawsystem.lawserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lawsystem.lawserver.dto.WhatsAppCode;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.service.WhatsAppService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    WhatsAppService whatsAppService;

    MemberRepository memberRepository;

    @GetMapping("/code/wa")
    public ResponseEntity<Void> createWhatsAppCode(HttpServletRequest request, HttpServletResponse response, String mobile) {

        Member member = memberRepository.findByPhone(mobile);

        if(member == null) return ResponseEntity.unprocessableEntity().build();

        WhatsAppCode code = generateCode();

        member.setLatestCode(code);

        memberRepository.save(member);

        whatsAppService.sendCode(code, mobile);

        return ResponseEntity.ok().build();
    }

    private WhatsAppCode generateCode() {
        String code = RandomStringUtils.randomNumeric(6);

        return new WhatsAppCode(code, 120);
    }
}
