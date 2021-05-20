package com.lawsystem.lawserver.security;


import java.util.ArrayList;
import java.util.Collection;

import com.lawsystem.lawserver.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {

    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return member.getLatestCode() != null ? "{noop}" + member.getLatestCode().getCode() : null;
    }

    @Override
    public String getUsername() {
        return member.getPhone();
    }

    @Override
    public boolean isAccountNonExpired() {
        return member.getLatestCode() != null && !member.getLatestCode().expired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
