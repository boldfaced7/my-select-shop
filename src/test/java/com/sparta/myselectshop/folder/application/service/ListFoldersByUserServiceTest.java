package com.sparta.myselectshop.folder.application.service;

import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sparta.myselectshop.folder.FolderTestUtil.*;

class ListFoldersByUserServiceTest {
    @DisplayName("회원 Id와 Pageable을 전달하면, 폴더 엔티티 페이지를 반환")
    @Test
    void givenAddFoldersCommand_whenFetchingFolders_thenReturnsLostOfFolders() {
        // Given
        var sut = new ListFoldersByUserService(
                (userId) -> List.of(folder(ID, USER_ID, NAME))
        );
        var command = new ListFoldersByUserCommand(USER_ID);

        // When
        var results = sut.listFoldersByUser(command);

        // Then
        results.forEach(result -> assertAll(result, ID, USER_ID, NAME));
    }

}