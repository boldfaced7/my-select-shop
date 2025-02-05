package com.sparta.myselectshop.productfolder.adaper.out.persistence;

import com.sparta.myselectshop.productfolder.application.port.out.FindProductFolderByIdsPort;
import com.sparta.myselectshop.productfolder.application.port.out.ListProductFoldersByFolderPort;
import com.sparta.myselectshop.productfolder.application.port.out.SaveProductFolderPort;
import com.sparta.myselectshop.productfolder.domain.ProductFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.sparta.myselectshop.productfolder.domain.ProductFolder.FolderId;
import static com.sparta.myselectshop.productfolder.domain.ProductFolder.ProductId;

@Repository
@RequiredArgsConstructor
public class ProductFolderPersistenceAdapter implements
        FindProductFolderByIdsPort,
        ListProductFoldersByFolderPort,
        SaveProductFolderPort {

    private final ProductFolderJpaRepository productFolderJpaRepository;

    @Override
    public Optional<ProductFolder> findByIds(ProductId productId, FolderId folderId) {
        return productFolderJpaRepository
                .findByProductIdAndFolderId(productId.value(), folderId.value())
                .map(ProductFolderMapper::toDomain);
    }

    @Override
    public Page<ProductFolder> listByFolderId(FolderId folderId, Pageable pageable) {
        return productFolderJpaRepository
                .findByFolderId(folderId.value(), pageable)
                .map(ProductFolderMapper::toDomain);
    }

    @Override
    public ProductFolder save(ProductFolder productFolder) {
        var source = ProductFolderMapper.toJpa(productFolder);
        var saved = productFolderJpaRepository.save(source);
        return ProductFolderMapper.toDomain(saved);
    }
}
