package com.sparta.myselectshop.user.application.service.validator;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sparta.myselectshop.user.UserTestUtil.*;

class AdminTokenValidatorTest {

    @DisplayName("검증 순서 요청 시, 검증 순서 반환")
    @Test
    void givenNothing_whenFetchingValidationOrder_ThenReturnsValidationOrder() {
        // Given
        var sut = new AdminTokenValidator();

        // When
        var order = sut.getValidationOrder();

        // Then
        Assertions.assertThat(order).isEqualTo(ValidationOrder.ADMIN_TOKEN);
    }

    @DisplayName("UserRole.User 전달 시, 예외를 던지지 않음")
    @Test
    void givenUserRoleUser_whenValidateAdminToken_thenThrowsNothing() {
        // Given
        var sut = new AdminTokenValidator();
        var command = new SignUpCommand(USERNAME, PASSWORD, EMAIL, USER, "");

        // When
        var action = Assertions.assertThatCode(
                () -> sut.validate(command)
        );
        // Then
        action.doesNotThrowAnyException();
    }

    @DisplayName("유효한 AdminToken 전달 시, 예외를 던지지 않음")
    @Test
    void givenValidAdminToken_whenValidateAdminToken_thenThrowsNothing() {
        // Given
        var sut = new AdminTokenValidator();
        setAdminToken(sut, ADMIN_TOKEN);
        var command = new SignUpCommand(USERNAME, PASSWORD, EMAIL, ADMIN, ADMIN_TOKEN);

        // When
        var action = Assertions.assertThatCode(
                () -> sut.validate(command)
        );
        // Then
        action.doesNotThrowAnyException();
    }

    @DisplayName("유효하지 않은 AdminToken 전달 시, 예외를 던짐")
    @Test
    void givenInvalidAdminToken_whenValidateAdminToken_thenThrowsException() {
        // Given
        var sut = new AdminTokenValidator();
        setAdminToken(sut, ADMIN_TOKEN);
        var command = new SignUpCommand(USERNAME, PASSWORD, EMAIL, ADMIN, "");

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.validate(command)
        );
        // Then
        thrown.isInstanceOf(IllegalArgumentException.class);
        thrown.hasMessage("관리자 암호가 틀려 등록이 불가능합니다.");
    }
}