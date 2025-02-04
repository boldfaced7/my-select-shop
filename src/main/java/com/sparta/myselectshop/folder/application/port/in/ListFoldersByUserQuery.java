package com.sparta.myselectshop.folder.application.port.in;

import com.sparta.myselectshop.folder.domain.Folder;
import org.springframework.data.domain.Page;

public interface ListFoldersByUserQuery {
    Page<Folder> listFoldersByUser(ListFoldersByUserCommand command);
}
