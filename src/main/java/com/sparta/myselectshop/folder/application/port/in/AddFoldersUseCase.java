package com.sparta.myselectshop.folder.application.port.in;

import com.sparta.myselectshop.folder.domain.Folder;

import java.util.List;

public interface AddFoldersUseCase {
    List<Folder> addFolders(AddFoldersCommand command);
}
