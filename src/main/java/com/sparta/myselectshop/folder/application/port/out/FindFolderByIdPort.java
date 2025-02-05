package com.sparta.myselectshop.folder.application.port.out;

import com.sparta.myselectshop.folder.domain.Folder;

import java.util.Optional;

public interface FindFolderByIdPort {
    Optional<Folder> findById(Folder.Id id);
}
