package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.RegisterProductCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sparta.myselectshop.product.ProductTestUtil.*;

class RegisterProductServiceTest {

    @DisplayName("[registerProduct] 제품 정보를 전달하면, 등록된 제품 엔티티를 반환")
    @Test
    void givenRegisterProductCommand_whenRegistering_thenReturnsSavedProduct() {
        // Given
        var sut = new RegisterProductService(
                product -> setId(product, ID)
        );
        var command = new RegisterProductCommand(TITLE, IMAGE, LINK, LOWEST_PRICE);

        // When
        var result = sut.registerProduct(command);

        // Then
        assertAll(result, ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE);
    }
}