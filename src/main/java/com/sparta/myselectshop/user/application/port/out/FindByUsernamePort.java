package com.sparta.myselectshop.user.application.port.out;

import com.sparta.myselectshop.user.domain.User;

import java.util.Optional;

import static com.sparta.myselectshop.user.domain.User.*;

public interface FindByUsernamePort {
    Optional<User> findByUsername(Username username);
}
