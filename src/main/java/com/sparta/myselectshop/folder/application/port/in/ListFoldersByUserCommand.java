package com.sparta.myselectshop.folder.application.port.in;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.sparta.myselectshop.folder.domain.Folder.UserId;

public record ListFoldersByUserCommand(
        UserId userId,
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
