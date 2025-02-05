package com.sparta.myselectshop.folder.application.port.out;

import com.sparta.myselectshop.folder.domain.Folder;

import java.util.List;

import static com.sparta.myselectshop.folder.domain.Folder.UserId;

public interface ListFoldersByUserPort {
    List<Folder> listByUserId(UserId userId);
}
