package com.sparta.myselectshop.user.application.port.out;

import com.sparta.myselectshop.user.domain.User;

import java.util.Optional;

import static com.sparta.myselectshop.user.domain.User.*;

public interface FindByEmailPort {
    Optional<User> findByEmail(Email email);
}
