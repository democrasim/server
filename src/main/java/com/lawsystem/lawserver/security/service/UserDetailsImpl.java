package com.lawsystem.lawserver.security.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lawsystem.lawserver.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {

    private String id;
    private String username;
    private String phone;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static UserDetailsImpl build(Member member) {

    }
}
