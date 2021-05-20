package com.lawsystem.lawserver.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lawsystem.lawserver.dto.LoginDto;
import com.lawsystem.lawserver.model.Member;
import com.lawsystem.lawserver.repo.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    private MemberRepository memberRepository;

    public AuthenticationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            LoginDto login = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);


            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getPhone(), login.getCode(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException("Could not read request: " + e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((UserDetailsImpl) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(SignatureAlgorithm.HS512, "TotallySecretKeyHereNothingToWorryAbout".getBytes())
                .compact();

        String phone = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();

        Member member = memberRepository.findByPhone(phone);

        member.setLatestCode(null);

        memberRepository.save(member);

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write((new Gson()).toJson(member));
    }
}
