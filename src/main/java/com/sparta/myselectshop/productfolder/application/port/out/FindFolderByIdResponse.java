package com.sparta.myselectshop.productfolder.application.port.out;

public record FindFolderByIdResponse(
        String folderId,
        String userId
) {
}
