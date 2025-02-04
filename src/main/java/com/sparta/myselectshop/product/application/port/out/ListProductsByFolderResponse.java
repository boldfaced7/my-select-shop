package com.sparta.myselectshop.product.application.port.out;

public record ListProductsByFolderResponse(
        String productFolderId,
        String folderId,
        String productId
) {}
