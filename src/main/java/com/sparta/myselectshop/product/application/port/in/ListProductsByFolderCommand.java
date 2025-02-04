package com.sparta.myselectshop.product.application.port.in;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.sparta.myselectshop.product.domain.Product.UserId;


public record ListProductsByFolderCommand(
        UserId userId,
        String folderId,
        int page,
        int size,
        String sortBy,
        boolean isAsc
) {
    public Pageable pageable() {
        var direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        var sort = Sort.by(direction, sortBy);
        return PageRequest.of(page-1, size, sort);
    }
}
