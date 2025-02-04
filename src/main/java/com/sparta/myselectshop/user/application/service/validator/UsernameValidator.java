package com.sparta.myselectshop.user.application.service.validator;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;
import com.sparta.myselectshop.user.application.port.out.FindByUsernamePort;
import com.sparta.myselectshop.user.application.port.out.SignUpRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernameValidator implements SignUpRequestValidator {

    private final FindByUsernamePort findByUsernamePort;

    @Override
    public int getValidationOrder() {
        return ValidationOrder.USERNAME;
    }

    @Override
    public void validate(SignUpCommand command) {
        findByUsernamePort.findByUsername(command.username())
                .ifPresent(user -> {throw new IllegalArgumentException("중복된 사용자가 존재합니다.");});
    }
}
