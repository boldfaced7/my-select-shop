package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.ListProductsByUserCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.sparta.myselectshop.MySelectShopTestUtil.*;
import static com.sparta.myselectshop.product.ProductTestUtil.*;

class ListProductsByUserServiceTest {

    @DisplayName("회원 Id와 Pageable을 전달하면, 제품 엔티티 페이지를 반환")
    @Test
    void givenListProductsByUserCommand_whenFetchingProducts_thenReturnsListOfProducts() {
        // Given
        var dummy = List.of(product(ID, USER_ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE));
        var sut = new ListProductsByUserService(
                (userId, pageable) -> new PageImpl<>(dummy)
        );
        var command = new ListProductsByUserCommand(USER_ID, PAGE, PAGE_SIZE, SORT_BY, IS_ASC);

        // When
        var results = sut.listProductsByUser(command);

        // Then
        results.forEach(result -> assertAll(
                result, ID, USER_ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE));
    }
}