package com.sparta.myselectshop.user.adapter.in.security;

import com.sparta.myselectshop.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    @Getter private final User user;

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var role = user.getRole();
        var authority = role.getAuthority();
        var simpleGrantedAuthority = new SimpleGrantedAuthority(authority);

        return List.of(simpleGrantedAuthority);
    }

}
