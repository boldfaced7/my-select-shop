package com.sparta.myselectshop.product.application.port.out;

import java.util.List;

public interface ListProductsByFolderPort {
    List<ListProductsByFolderResponse> listByFolderId(
            ListProductsByFolderRequest request);
}
