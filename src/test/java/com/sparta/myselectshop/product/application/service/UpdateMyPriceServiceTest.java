package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.UpdateMyPriceCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.sparta.myselectshop.product.ProductTestUtil.*;

class UpdateMyPriceServiceTest {

    @DisplayName("[updateMyPrice] 제품 id와 나의 가격을 전달하면, 갱신된 제품 엔티티를 반환")
    @Test
    void givenUpdateMyPriceCommand_whenUpdating_thenReturnsUpdatedProduct() {
        // Given
        var sut = new UpdateMyPriceService(
                id -> Optional.of(product(ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE)),
                product -> product
        );
        var command = new UpdateMyPriceCommand(ID, NEW_MY_PRICE);

        // When
        var result = sut.updateMyPrice(command);

        // Then
        assertAll(result, ID, TITLE, IMAGE, LINK, LOWEST_PRICE, NEW_MY_PRICE);
    }

    @DisplayName("[updateMyPrice] 존재하지 않는 제품 id를 전달하면, 예외를 던짐")
    @Test
    void givenWringProductId_whenUpdating_thenThrowsException() {
        // Given
        var sut = new UpdateMyPriceService(
                id -> Optional.empty(),
                null
        );
        var command = new UpdateMyPriceCommand(ID, NEW_MY_PRICE);

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.updateMyPrice(command)
        );
        // Then
        thrown.isInstanceOf(IllegalArgumentException.class);
    }
}