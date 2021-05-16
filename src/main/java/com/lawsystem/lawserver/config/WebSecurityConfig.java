package com.lawsystem.lawserver.config;

import java.util.Collections;

import com.lawsystem.lawserver.repo.MemberRepository;
import com.lawsystem.lawserver.security.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

    private UserDetailsServiceImpl userDetailsService;
    private MemberRepository memberRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(HttpMethod.POST, "/member/request_register")
                .mvcMatchers(HttpMethod.GET, "/code/wa");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new AuthenticationFilter(authenticationManager(), memberRepository))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .addFilterAfter(new ApiKeyAuthenticationFilter(apiKeyAuth()), AnonymousAuthenticationFilter.class);

    }

    @Bean
    public AuthenticationManager apiKeyAuth() {
        return new ProviderManager(Collections.singletonList(apiKeyAuthenticationProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
