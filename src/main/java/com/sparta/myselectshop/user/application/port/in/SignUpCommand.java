package com.sparta.myselectshop.user.application.port.in;

import com.sparta.myselectshop.user.domain.UserRole;

import static com.sparta.myselectshop.user.domain.User.*;

public record SignUpCommand(
        Username username,
        Password password,
        Email email,
        UserRole userRole,
        String adminToken
) {}
