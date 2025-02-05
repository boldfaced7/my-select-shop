package com.sparta.myselectshop.product.application.port.out;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListProductFoldersByFolderPort {
    List<ProductFolderResponse> listByFolderId(ProductFolderRequest request);

    record ProductFolderRequest(
            String folderId,
            Pageable pageable

    ) {}

    record ProductFolderResponse(
            String productFolderId,
            String folderId,
            String productId
    ) {}
}
