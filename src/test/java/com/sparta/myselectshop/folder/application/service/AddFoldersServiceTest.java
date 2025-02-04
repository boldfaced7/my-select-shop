package com.sparta.myselectshop.folder.application.service;

import com.sparta.myselectshop.folder.application.port.in.AddFoldersCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sparta.myselectshop.folder.FolderTestUtil.*;

class AddFoldersServiceTest {

    @DisplayName("회원 Id와 폴더 이름 리스트를 전달하면, 저장된 폴더 엔티티 리스트를 반환")
    @Test
    void givenAddFoldersCommand_whenAddingFolders_thenReturnsSavedFolders() {
        // Given
        var sut = new AddFoldersService(
                (userId, names) -> false,
                folders -> folders.stream()
                        .map(folder -> setId(folder, ID)).toList()
        );
        var command = new AddFoldersCommand(USER_ID, List.of(NAME));

        // When
        var results = sut.addFolders(command);

        // Then
        results.forEach(result -> assertAll(result, ID, USER_ID, NAME));
    }

    @DisplayName("이미 사용중인 폴더 이름이 전달되면, 예욀르 던짐")
    @Test
    void givenInvalidName_whenAddingFolders_thenThrowsException() {
        // Given
        var sut = new AddFoldersService(
                (userId, names) -> true,
                null
        );
        var command = new AddFoldersCommand(USER_ID, List.of(NAME));

        // When
        var thrown = Assertions.assertThatThrownBy(
                () -> sut.addFolders(command)
        );
        // Then
        thrown.isInstanceOf(IllegalArgumentException.class);
        thrown.hasMessageContaining("폴더명이 중복되었습니다.");
    }
}