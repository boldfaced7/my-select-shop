package com.sparta.myselectshop.productfolder.application.port.out;

import com.sparta.myselectshop.productfolder.domain.ProductFolder;

public interface SaveProductFolderPort {
    ProductFolder save(ProductFolder productFolder);
}
