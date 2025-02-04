package com.sparta.myselectshop.product.application.port.out;

import org.springframework.data.domain.Pageable;

public record ListProductsByFolderRequest(
        String folderId,
        Pageable pageable
) {}
