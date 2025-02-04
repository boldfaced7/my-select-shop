package com.sparta.myselectshop.user.application.port.out;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;

public interface SignUpRequestValidator {
    int getValidationOrder();
    void validate(SignUpCommand command);
}
