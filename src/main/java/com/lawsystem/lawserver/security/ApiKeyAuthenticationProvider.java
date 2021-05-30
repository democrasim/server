package com.lawsystem.lawserver.security;

import com.lawsystem.lawserver.config.WhatsAppConfiguration;
import com.lawsystem.lawserver.model.security.ApiKeyAuthenticationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@AllArgsConstructor
@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private WhatsAppConfiguration whatsAppConfiguration;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = (String) authentication.getPrincipal();

        Authentication contextAuthentication = SecurityContextHolder.getContext().getAuthentication();

        if (contextAuthentication
                .isAuthenticated() && !(contextAuthentication instanceof AnonymousAuthenticationToken))
            return contextAuthentication;

        if (ObjectUtils.isEmpty(apiKey)) {
            throw new InsufficientAuthenticationException("No API key in request");
        } else {
            if (whatsAppConfiguration.getApiKey().equals(apiKey)) {
                return new ApiKeyAuthenticationToken(apiKey, true);
            }
            throw new BadCredentialsException("API Key is invalid");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
