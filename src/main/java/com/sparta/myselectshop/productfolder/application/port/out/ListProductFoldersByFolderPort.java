package com.sparta.myselectshop.productfolder.application.port.out;

import com.sparta.myselectshop.productfolder.domain.ProductFolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.sparta.myselectshop.productfolder.domain.ProductFolder.FolderId;

public interface ListProductFoldersByFolderPort {
    Page<ProductFolder> listByFolderId(FolderId folderId, Pageable pageable);

}
