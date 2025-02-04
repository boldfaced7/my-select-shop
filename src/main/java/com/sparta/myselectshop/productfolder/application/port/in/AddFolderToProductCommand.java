package com.sparta.myselectshop.productfolder.application.port.in;

import com.sparta.myselectshop.productfolder.domain.ProductFolder;

public record AddFolderToProductCommand(
        ProductFolder.UserId userId,
        ProductFolder.ProductId productId,
        ProductFolder.FolderId folderId
) {
}
