package com.sparta.myselectshop.folder.application.service;

import com.sparta.myselectshop.folder.application.port.in.AddFoldersCommand;
import com.sparta.myselectshop.folder.application.port.in.AddFoldersUseCase;
import com.sparta.myselectshop.folder.application.port.out.CheckNamesDuplicationPort;
import com.sparta.myselectshop.folder.application.port.out.SaveFoldersPort;
import com.sparta.myselectshop.folder.domain.Folder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sparta.myselectshop.folder.domain.Folder.*;

@Service
@RequiredArgsConstructor
public class AddFoldersService implements AddFoldersUseCase {

    private final CheckNamesDuplicationPort checkNamesDuplicationPort;
    private final SaveFoldersPort saveFoldersPort;

    @Override
    public List<Folder> addFolders(AddFoldersCommand command) {
        throwIfNameDuplicated(command.userId(), command.names());

        return command.names().stream()
                .map(name -> generate(command.userId(), name))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        saveFoldersPort::saveAll
                ));
    }

    private void throwIfNameDuplicated(UserId userId, List<Name> names) {
        if (checkNamesDuplicationPort.isDuplicated(userId, names)) {
            throw new IllegalArgumentException("폴더명이 중복되었습니다.");
        }
    }
}
