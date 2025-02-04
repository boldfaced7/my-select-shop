package com.sparta.myselectshop.user.application.service;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;
import com.sparta.myselectshop.user.application.port.in.SignUpUseCase;
import com.sparta.myselectshop.user.application.port.out.EncodePasswordPort;
import com.sparta.myselectshop.user.application.port.out.SaveUserPort;
import com.sparta.myselectshop.user.application.port.out.SignUpRequestValidator;
import com.sparta.myselectshop.user.domain.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.sparta.myselectshop.user.domain.User.Password;
import static com.sparta.myselectshop.user.domain.User.generate;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final List<SignUpRequestValidator> validators;
    private final EncodePasswordPort encodePasswordPort;
    private final SaveUserPort saveUserPort;

    @PostConstruct
    private void init() {
        validators.sort(Comparator.comparingInt(
                SignUpRequestValidator::getValidationOrder));
    }

    @Override
    public User signUp(SignUpCommand command) {
        validators.forEach(validator -> validator.validate(command));
        Password encoded = encodePasswordPort.encodePassword(command.password());

        User toBeSaved = generate(
                command.username(),
                encoded,
                command.email(),
                command.userRole()
        );
        return saveUserPort.save(toBeSaved);
    }
}
