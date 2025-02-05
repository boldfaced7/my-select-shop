package com.sparta.myselectshop.productfolder.adaper.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductFolderJpaRepository extends Repository<ProductFolderJpaEntity, Long> {
    Optional<ProductFolderJpaEntity> findByProductIdAndFolderId(String productId, String folderId);
    Page<ProductFolderJpaEntity> findByFolderId(String folderId, Pageable pageable);
    ProductFolderJpaEntity save(ProductFolderJpaEntity productFolder);
}
