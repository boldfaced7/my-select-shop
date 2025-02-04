package com.sparta.myselectshop.user.adapter.out.encoder;

import com.sparta.myselectshop.user.application.port.out.EncodePasswordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.sparta.myselectshop.user.domain.User.*;

@Component
@RequiredArgsConstructor
public class NoOpPasswordEncoder implements EncodePasswordPort {


    @Override
    public Password encodePassword(Password rawPassword) {
        return rawPassword;
    }
}
