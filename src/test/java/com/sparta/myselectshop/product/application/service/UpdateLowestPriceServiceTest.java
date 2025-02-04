package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.UpdateLowestPriceCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.sparta.myselectshop.product.ProductTestUtil.*;

class UpdateLowestPriceServiceTest {

    @DisplayName("[updateLowestPrice] 제품 id와 최저 가격을 전달하면, 갱신된 제품 엔티티를 반환")
    @Test
    void givenUpdateLowestPriceCommand_whenUpdating_thenReturnsUpdatedProduct() {
        // Given
        var sut = new UpdateLowestPriceService(
                id -> Optional.of(product(ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE)),
                product -> product
        );
        var command = new UpdateLowestPriceCommand(ID, NEW_LOWEST_PRICE);

        // When
        var result = sut.updateLowestPrice(command);

        // Then
        assertAll(result, ID, TITLE, IMAGE, LINK, NEW_LOWEST_PRICE, MY_PRICE);
    }

    @DisplayName("[updateLowestPrice] 존재하지 않는 제품 id를 전달하면, 예외를 던짐")
    @Test
    void givenWringProductId_whenUpdating_thenThrowsException() {
        // Given
        var sut = new UpdateLowestPriceService(
                id -> Optional.empty(),
                null
        );
        var command = new UpdateLowestPriceCommand(ID, NEW_LOWEST_PRICE);

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.updateLowestPrice(command)
        );
        // Then
        thrown.isInstanceOf(IllegalArgumentException.class);
    }
}