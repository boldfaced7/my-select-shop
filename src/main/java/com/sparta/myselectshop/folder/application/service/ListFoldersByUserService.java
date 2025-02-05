package com.sparta.myselectshop.folder.application.service;

import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserCommand;
import com.sparta.myselectshop.folder.application.port.in.ListFoldersByUserQuery;
import com.sparta.myselectshop.folder.application.port.out.ListFoldersByUserPort;
import com.sparta.myselectshop.folder.domain.Folder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListFoldersByUserService implements ListFoldersByUserQuery {

    private final ListFoldersByUserPort listFoldersByUserPort;

    @Override
    public List<Folder> listFoldersByUser(ListFoldersByUserCommand command) {
        return listFoldersByUserPort.listByUserId(command.userId());
    }
}
