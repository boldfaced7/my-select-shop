package com.sparta.myselectshop.productfolder.adaper.out.internal;

import com.sparta.myselectshop.common.IdParser;
import com.sparta.myselectshop.folder.adapter.out.persistence.FolderJpaEntity;
import com.sparta.myselectshop.folder.adapter.out.persistence.FolderJpaRepository;
import com.sparta.myselectshop.productfolder.application.port.out.FindFolderByIdPort;
import com.sparta.myselectshop.productfolder.domain.ProductFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindFolderByIdAdapter implements FindFolderByIdPort {

    private final FolderJpaRepository folderJpaRepository;

    @Override
    public Optional<FolderResponse> findById(FolderRequest folderRequest) {
        Long folderId = IdParser.parseLong(folderRequest.folderId().value());
        return folderJpaRepository.findById(folderId)
                .map(this::toResponse);
    }

    private FolderResponse toResponse(FolderJpaEntity folder) {
        return new FolderResponse(
                new ProductFolder.FolderId(folder.getId().toString()),
                new ProductFolder.UserId(folder.getUserId())
        );
    }
}
