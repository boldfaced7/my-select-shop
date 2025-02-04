package com.sparta.myselectshop.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {

    private final String id;
    private final String username;
    private final String password;
    private final String email;
    private final UserRole role;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    public static User generate(
            Username username,
            Password password,
            Email email,
            UserRole role
    ) {
        return new User(
                null,
                username.value(),
                password.value(),
                email.value(),
                role,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static User generate(
            Id id,
            Username username,
            Password password,
            Email email,
            UserRole role,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new User(
                id.value(),
                username.value(),
                password.value(),
                email.value(),
                role,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public record Id(String value) {}
    public record Username(String value) {}
    public record Password(String value) {}
    public record Email(String value) {}
}
