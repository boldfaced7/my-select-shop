package com.sparta.myselectshop.user.application.service.validator;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;
import com.sparta.myselectshop.user.application.port.out.FindByEmailPort;
import com.sparta.myselectshop.user.application.port.out.SignUpRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailValidator implements SignUpRequestValidator {

    private final FindByEmailPort findByEmailPort;


    @Override
    public int getValidationOrder() {
        return ValidationOrder.EMAIL;
    }

    @Override
    public void validate(SignUpCommand command) {
        findByEmailPort.findByEmail(command.email())
                .ifPresent(user -> {throw new IllegalArgumentException("중복된 Email 입니다.");});
    }
}
