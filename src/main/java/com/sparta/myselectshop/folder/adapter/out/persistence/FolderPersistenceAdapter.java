package com.sparta.myselectshop.folder.adapter.out.persistence;

import com.sparta.myselectshop.common.IdParser;
import com.sparta.myselectshop.folder.application.port.out.CheckNamesDuplicationPort;
import com.sparta.myselectshop.folder.application.port.out.FindFolderByIdPort;
import com.sparta.myselectshop.folder.application.port.out.ListFoldersByUserPort;
import com.sparta.myselectshop.folder.application.port.out.SaveFoldersPort;
import com.sparta.myselectshop.folder.domain.Folder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sparta.myselectshop.folder.domain.Folder.*;

@Repository
@RequiredArgsConstructor
public class FolderPersistenceAdapter implements
        CheckNamesDuplicationPort,
        FindFolderByIdPort,
        ListFoldersByUserPort,
        SaveFoldersPort {

    private final FolderJpaRepository folderJpaRepository;

    @Override
    public boolean isDuplicated(UserId id, List<Name> names) {
        return names.stream()
                .map(Name::value)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        folderJpaRepository::existsByNameIn
                ));
    }

    @Override
    public Optional<Folder> findById(Id id) {
        return folderJpaRepository
                .findById(IdParser.parseLong(id.value()))
                .map(FolderMapper::toDomain);
    }

    @Override
    public List<Folder> listByUserId(UserId userId) {
        return folderJpaRepository
                .findByUserId(userId.value())
                .stream()
                .map(FolderMapper::toDomain)
                .toList();
    }

    @Override
    public List<Folder> saveAll(List<Folder> folders) {
        var source = folders.stream()
                .map(FolderMapper::toJpa)
                .toList();

        var saved = folderJpaRepository.saveAll(source);

        return saved.stream()
                .map(FolderMapper::toDomain)
                .toList();
    }
}
