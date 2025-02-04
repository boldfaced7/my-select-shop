package com.sparta.myselectshop.productfolder.application.port.out;

import com.sparta.myselectshop.productfolder.domain.ProductFolder;

import java.util.Optional;

import static com.sparta.myselectshop.productfolder.domain.ProductFolder.FolderId;
import static com.sparta.myselectshop.productfolder.domain.ProductFolder.ProductId;

public interface FindProductFolderByIdsPort {
    Optional<ProductFolder> findByIds(ProductId productId, FolderId folderId);
}
