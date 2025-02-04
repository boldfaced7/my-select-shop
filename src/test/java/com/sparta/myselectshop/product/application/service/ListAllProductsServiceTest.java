package com.sparta.myselectshop.product.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sparta.myselectshop.product.ProductTestUtil.*;

class ListAllProductsServiceTest {

    @DisplayName("[listAllProducts] 호출 시 제품 리스트를 반환")
    @Test
    void givenNothing_whenFetching_thenReturnsProductList() {
        // Given
        var sut = new ListAllProductsService(
                () -> List.of(product(ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE))
        );
        // When
        var results = sut.listAllProducts();

        // Then
        results.forEach(result -> assertAll(
                result, ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE));
    }
}