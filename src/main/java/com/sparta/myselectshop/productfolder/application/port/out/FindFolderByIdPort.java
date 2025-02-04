package com.sparta.myselectshop.productfolder.application.port.out;

import java.util.Optional;

public interface FindFolderByIdPort {
    Optional<FindFolderByIdResponse> findById(FindFolderByIdRequest request);
}
