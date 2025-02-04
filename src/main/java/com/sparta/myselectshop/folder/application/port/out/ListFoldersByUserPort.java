package com.sparta.myselectshop.folder.application.port.out;

import com.sparta.myselectshop.folder.domain.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.sparta.myselectshop.folder.domain.Folder.UserId;

public interface ListFoldersByUserPort {
    Page<Folder> findByUserId(UserId userId, Pageable pageable);
}
