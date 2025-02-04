package com.sparta.myselectshop.user.application.service.validator;

import com.sparta.myselectshop.user.application.port.in.SignUpCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.sparta.myselectshop.user.UserTestUtil.*;

class UsernameValidatorTest {

    @DisplayName("검증 순서 요청 시, 검증 순서 반환")
    @Test
    void givenNothing_whenFetchingValidationOrder_ThenReturnsValidationOrder() {
        // Given
        var sut = new UsernameValidator(null);

        // When
        var order = sut.getValidationOrder();

        // Then
        Assertions.assertThat(order).isEqualTo(ValidationOrder.USERNAME);
    }

    @DisplayName("중복되지 않는 username 전달 시, 예외를 던지지 않음")
    @Test
    void givenValidUsername_whenValidateUsername_thenThrowsNothing() {
        // Given
        var sut = new UsernameValidator(
                email -> Optional.empty()
        );
        var command = new SignUpCommand(USERNAME, PASSWORD, EMAIL, USER, "");

        // When
        var action = Assertions.assertThatCode(
                () -> sut.validate(command)
        );
        // Then
        action.doesNotThrowAnyException();
    }


    @DisplayName("중복된 username 전달 시, 예외를 던짐")
    @Test
    void givenInvalidUsername_whenValidateUsername_thenThrowsException() {
        // Given
        var sut = new UsernameValidator(
                username -> Optional.of(user(ID, USERNAME, PASSWORD, EMAIL, USER))
        );
        var command = new SignUpCommand(USERNAME, PASSWORD, EMAIL, USER, "");

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.validate(command)
        );
        // Then
        thrown.isInstanceOf(IllegalArgumentException.class);
        thrown.hasMessage("중복된 사용자가 존재합니다.");
    }

}