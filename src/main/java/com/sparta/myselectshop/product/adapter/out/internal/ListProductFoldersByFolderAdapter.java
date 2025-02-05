package com.sparta.myselectshop.product.adapter.out.internal;

import com.sparta.myselectshop.product.application.port.out.ListProductFoldersByFolderPort;
import com.sparta.myselectshop.productfolder.adaper.out.persistence.ProductFolderJpaEntity;
import com.sparta.myselectshop.productfolder.adaper.out.persistence.ProductFolderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListProductFoldersByFolderAdapter implements ListProductFoldersByFolderPort {

    private final ProductFolderJpaRepository productFolderJpaRepository;

    @Override
    public List<ProductFolderResponse> listByFolderId(ProductFolderRequest request) {
        return productFolderJpaRepository
                .findByFolderId(request.folderId(), request.pageable())
                .map(this::toResponse)
                .toList();
    }

    private ProductFolderResponse toResponse(ProductFolderJpaEntity productFolder) {
        return new ProductFolderResponse(
                productFolder.getId().toString(),
                productFolder.getProductId(),
                productFolder.getFolderId()
        );
    }
}
