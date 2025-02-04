package com.sparta.myselectshop.user.adapter.in.security;

import com.sparta.myselectshop.user.application.port.out.FindByUsernamePort;
import com.sparta.myselectshop.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final FindByUsernamePort findByUsernamePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsernamePort.findByUsername(new User.Username(username))
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

    }
}
