package com.sparta.myselectshop.user.adapter.in.web;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;
import com.sparta.myselectshop.user.application.port.in.SignUpUseCase;
import com.sparta.myselectshop.user.domain.User;
import com.sparta.myselectshop.user.domain.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignUpController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("/user/signup")
    public String signup(@Valid Request request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            bindingResult.getFieldErrors().forEach(
                    error -> log.error("{} 필드: {}", error.getField(), error.getDefaultMessage()));
            return "redirect:/api/user/signup";
        }
        var command = toCommand(request);
        signUpUseCase.signUp(command);
        return "redirect:/api/user/login-page";
    }

    private SignUpCommand toCommand(Request request) {
        return new SignUpCommand(
                new User.Username(request.username()),
                new User.Password(request.password()),
                new User.Email(request.email()),
                (request.admin) ? UserRole.ADMIN : UserRole.USER,
                request.adminToken()
        );
    }

    public record Request(
            @NotBlank String username,
            @NotBlank String password,
            @Email String email,
            boolean admin,
            String adminToken
    ) {

        public Request(String username, String password, String email) {
            this(username, password, email, false, "");
        }
    }
}
