package com.sparta.myselectshop.folder.application.service;

import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserCommand;
import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserQuery;
import com.sparta.myselectshop.folder.application.port.out.ListFoldersByUserPort;
import com.sparta.myselectshop.folder.domain.Folder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListFoldersByUserService implements ListFoldersByUserQuery {

    private final ListFoldersByUserPort listFoldersByUserPort;

    @Override
    public Page<Folder> listFoldersByUser(ListFoldersByUserCommand command) {
        return listFoldersByUserPort.findByUserId(command.userId(), command.pageable());
    }
}
