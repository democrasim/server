package com.lawsystem.lawserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {
    @GetMapping("/code/wa")
    public void createWhatsappCode(HttpServletRequest httpServletRequest, HttpServletReque)
}
