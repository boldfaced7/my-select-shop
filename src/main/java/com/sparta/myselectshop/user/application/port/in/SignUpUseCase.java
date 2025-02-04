package com.sparta.myselectshop.user.application.port.in;

import com.sparta.myselectshop.user.domain.User;

public interface SignUpUseCase {
    User signUp(SignUpCommand command);
}
