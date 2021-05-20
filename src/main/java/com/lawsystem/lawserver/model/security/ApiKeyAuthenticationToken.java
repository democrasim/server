package com.lawsystem.lawserver.model.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.AuthorityUtils;

@Transient
public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {
    private String apiKey;

    public ApiKeyAuthenticationToken(String apiKey, boolean authenticated) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.apiKey = apiKey;
        setAuthenticated(authenticated);
    }
    public ApiKeyAuthenticationToken(String apiKey) {
        this(apiKey, false);
    }

    public ApiKeyAuthenticationToken() {
        this(null, false);
    }



    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
