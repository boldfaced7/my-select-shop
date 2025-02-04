package com.sparta.myselectshop.productfolder.application.service;

import com.sparta.myselectshop.productfolder.application.port.in.AddFolderToProductCommand;
import com.sparta.myselectshop.productfolder.application.port.out.FindFolderByIdResponse;
import com.sparta.myselectshop.productfolder.application.port.out.FindProductByIdResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.sparta.myselectshop.productfolder.ProductFolderTestUtil.*;

class AddFolderToProductServiceTest {

    @DisplayName("회원, 제품, 폴더 id 전달 시, 저장된 제품-폴더 객체 반환")
    @Test
    void givenAddFolderToProductCommand_whenAddFolderToProduct_thenReturnsSavedProductFolder() {
        // Given
        var sut = new AddFolderToProductService(
                request -> Optional.of(new FindProductByIdResponse(null, USER_ID.value(), null)),
                request -> Optional.of(new FindFolderByIdResponse(null, USER_ID.value())),
                (productId, folderId) -> Optional.empty(),
                productFolder -> setId(productFolder, ID)
        );
        var command = new AddFolderToProductCommand(USER_ID, PRODUCT_ID, FOLDER_ID);

        // When
        var result = sut.addFolderToProduct(command);

        // then
        assertAll(result, ID, USER_ID, PRODUCT_ID, FOLDER_ID);
    }

    @DisplayName("잘못된 제품 id 전달 시, 예외를 던짐")
    @Test
    void givenInvalidProductId_whenAddFolderToProduct_thenThrowsException() {
        // Given
        var sut = new AddFolderToProductService(
                request -> Optional.empty(),
                null,
                null,
                null
        );
        var command = new AddFolderToProductCommand(USER_ID, PRODUCT_ID, FOLDER_ID);

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.addFolderToProduct(command)
        );
        // then
        thrown.isInstanceOf(NullPointerException.class);
        thrown.hasMessage("해당 상품이 존재하지 않습니다.");
    }

    @DisplayName("잘못된 폴더 id 전달 시, 예외를 던짐")
    @Test
    void givenInvalidFolderId_whenAddFolderToProduct_thenThrowsException() {
        // Given
        var sut = new AddFolderToProductService(
                request -> Optional.of(new FindProductByIdResponse(null, USER_ID.value(), null)),
                request -> Optional.empty(),
                null,
                null
        );
        var command = new AddFolderToProductCommand(USER_ID, PRODUCT_ID, FOLDER_ID);

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.addFolderToProduct(command)
        );
        // then
        thrown.isInstanceOf(NullPointerException.class);
        thrown.hasMessage("해당 폴더가 존재하지 않습니다.");
    }

    @DisplayName("잘못된 회원 id 전달 시, 예외를 던짐")
    @Test
    void givenInvalidUserId_whenAddFolderToProduct_thenThrowsException() {
        // Given
        var sut = new AddFolderToProductService(
                request -> Optional.of(new FindProductByIdResponse(null, USER_ID.value(), null)),
                request -> Optional.of(new FindFolderByIdResponse(null, USER_ID.value())),
                null,
                null
        );
        var command = new AddFolderToProductCommand(INVALID_USER_ID, PRODUCT_ID, FOLDER_ID);

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.addFolderToProduct(command)
        );
        // then
        thrown.isInstanceOf(IllegalArgumentException.class);
        thrown.hasMessage("회원님의 관심상품이 아니거나, 회원님의 폴더가 아닙니다.");
    }

    @DisplayName("제품, 폴더 id가 중복인 경우, 예외를 던짐")
    @Test
    void givenSavedProductIdAndFolderId_whenAddFolderToProduct_thenThrowsException() {
        // Given
        var sut = new AddFolderToProductService(
                request -> Optional.of(new FindProductByIdResponse(null, USER_ID.value(), null)),
                request -> Optional.of(new FindFolderByIdResponse(null, USER_ID.value())),
                (productId, folderId) -> Optional.of(productFolder(ID, USER_ID, PRODUCT_ID, FOLDER_ID)),
                null
        );
        var command = new AddFolderToProductCommand(USER_ID, PRODUCT_ID, FOLDER_ID);

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.addFolderToProduct(command)
        );
        // then
        thrown.isInstanceOf(IllegalArgumentException.class);
        thrown.hasMessage("중복된 폴더입니다.");
    }
}