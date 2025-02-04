package com.sparta.myselectshop.user.application.service.validator;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;
import com.sparta.myselectshop.user.application.port.out.SignUpRequestValidator;
import com.sparta.myselectshop.user.domain.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminTokenValidator implements SignUpRequestValidator {

    @Value("${admin.token}")
    private String adminToken;

    @Override
    public int getValidationOrder() {
        return ValidationOrder.ADMIN_TOKEN;
    }

    @Override
    public void validate(SignUpCommand command) {
        if (command.userRole() == UserRole.ADMIN
                && !adminToken.equals(command.adminToken())) {
            throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
        }
    }
}
