package com.sparta.myselectshop.productfolder.application.port.in;

import com.sparta.myselectshop.productfolder.domain.ProductFolder;

public interface AddFolderToProductUseCase {
    ProductFolder addFolderToProduct(AddFolderToProductCommand command);
}
