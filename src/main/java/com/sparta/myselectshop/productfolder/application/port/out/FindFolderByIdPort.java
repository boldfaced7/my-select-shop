package com.sparta.myselectshop.productfolder.application.port.out;

import java.util.Optional;

import static com.sparta.myselectshop.productfolder.domain.ProductFolder.FolderId;
import static com.sparta.myselectshop.productfolder.domain.ProductFolder.UserId;

public interface FindFolderByIdPort {
    Optional<FolderResponse> findById(FolderRequest folderRequest);

    record FolderRequest(
            FolderId folderId
    ) {}

    record FolderResponse(
            FolderId folderId,
            UserId userId
    ) {}
}
