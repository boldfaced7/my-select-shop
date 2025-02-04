package com.sparta.myselectshop.folder.application.service;

import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.sparta.myselectshop.MySelectShopTestUtil.*;
import static com.sparta.myselectshop.folder.FolderTestUtil.*;

class ListFoldersByUserServiceTest {
    @DisplayName("회원 Id와 Pageable을 전달하면, 폴더 엔티티 페이지를 반환")
    @Test
    void givenAddFoldersCommand_whenFetchingFolders_thenReturnsLostOfFolders() {
        // Given
        var dummy = List.of(folder(ID, USER_ID, NAME));
        var sut = new ListFoldersByUserService(
                (userId, pageable) -> new PageImpl<>(dummy)
        );
        var command = new ListFoldersByUserCommand(USER_ID, PAGE, PAGE_SIZE, SORT_BY, IS_ASC);

        // When
        var results = sut.listFoldersByUser(command);

        // Then
        results.forEach(result -> assertAll(result, ID, USER_ID, NAME));
    }

}