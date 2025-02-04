package com.sparta.myselectshop.user;

import com.sparta.myselectshop.user.application.service.validator.AdminTokenValidator;
import com.sparta.myselectshop.user.domain.User;
import com.sparta.myselectshop.user.domain.UserRole;
import org.assertj.core.api.Assertions;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static com.sparta.myselectshop.user.domain.User.*;

public class UserTestUtil {
    public final static Id ID = new Id("1");
    public final static Username USERNAME = new Username("username");
    public final static Password PASSWORD = new Password("password");
    public final static Email EMAIL = new Email("email");
    public final static UserRole USER = UserRole.USER;
    public final static UserRole ADMIN = UserRole.ADMIN;
    public final static String ADMIN_TOKEN = "admin_token";


    public static User user(
            Id id,
            Username username,
            Password password,
            Email email,
            UserRole role
    ) {
        return generate(
                id,
                username,
                password,
                email,
                role,
                LocalDateTime.MIN,
                LocalDateTime.MIN,
                null
        );
    }

    public static User setId(User target, Id id) {
        ReflectionTestUtils.setField(target, "id", id.value());
        return target;
    }

    public static void setAdminToken(AdminTokenValidator target, String adminToken) {
        ReflectionTestUtils.setField(target, "adminToken", adminToken);
    }


    public static void assertAll(
            User user,
            Id id,
            Username username,
            Password password,
            Email email,
            UserRole role
    ) {
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isEqualTo(id.value());
        Assertions.assertThat(user.getUsername()).isEqualTo(username.value());
        Assertions.assertThat(user.getPassword()).isEqualTo(password.value());
        Assertions.assertThat(user.getEmail()).isEqualTo(email.value());
        Assertions.assertThat(user.getRole()).isEqualTo(role);
    }
}
