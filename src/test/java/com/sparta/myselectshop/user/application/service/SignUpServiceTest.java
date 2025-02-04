package com.sparta.myselectshop.user.application.service;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;
import com.sparta.myselectshop.user.domain.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.sparta.myselectshop.user.UserTestUtil.*;

class SignUpServiceTest {

    @DisplayName("회원 가입 정보를 전달하면, 가입된 회원 정보를 반환")
    @ParameterizedTest
    @MethodSource
    void givenSignUpCommand_whenSignUp_thenReturnsRegisteredUser(UserRole role, String adminToken) {
        // Given
        var sut = new SignUpService(
                List.of(),
                password -> password,
                user -> setId(user, ID)
        );
        var command = new SignUpCommand(USERNAME, PASSWORD, EMAIL, role, adminToken);

        // When
        var result = sut.signUp(command);

        // Then
        assertAll(result, ID, USERNAME, PASSWORD, EMAIL, role);
    }

    static Stream<Arguments> givenSignUpCommand_whenSignUp_thenReturnsRegisteredUser() {
        return Stream.of(
                Arguments.of(USER, ""),
                Arguments.of(ADMIN, ADMIN_TOKEN)
        );
    }
}