package com.lawsystem.lawserver.config;

import com.lawsystem.lawserver.security.AuthenticationFilter;
import com.lawsystem.lawserver.security.AuthorizationFilter;
import com.lawsystem.lawserver.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "member/request_register").permitAll()
                .mvcMatchers(HttpMethod.GET, "code/wa").permitAll()
                .mvcMatchers(HttpMethod.POST, "login").permitAll()
                .anyRequest().authenticated()
                .and()
        .addFilter(new AuthenticationFilter(authenticationManager()))
        .addFilter(new AuthorizationFilter(authenticationManager()))
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
