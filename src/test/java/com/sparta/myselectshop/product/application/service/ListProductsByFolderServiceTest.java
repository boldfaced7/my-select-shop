package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.ListProductsByFolderCommand;
import com.sparta.myselectshop.product.application.port.out.ListProductsByFolderResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sparta.myselectshop.MySelectShopTestUtil.*;
import static com.sparta.myselectshop.product.ProductTestUtil.*;

class ListProductsByFolderServiceTest {

    @DisplayName("회원 Id와 Pageable을 전달하면, 제품 엔티티 페이지를 반환")
    @Test
    void givenListProductsByFolderCommand_whenFetchingProducts_thenReturnsListOfProducts() {
        // Given
        var sut = new ListProductsByFolderService(
                req -> List.of(new ListProductsByFolderResponse(PRODUCT_FOLDER_ID, ID.value(), FOLDER_ID)),
                ids -> List.of(product(ID, USER_ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE))
        );
        var command = new ListProductsByFolderCommand(USER_ID, FOLDER_ID, PAGE, PAGE_SIZE, SORT_BY, IS_ASC);

        // When
        var results = sut.listProductsByFolder(command);

        // Then
        results.forEach(result -> assertAll(
                result, ID, USER_ID, TITLE, IMAGE, LINK, LOWEST_PRICE, MY_PRICE));
    }
}