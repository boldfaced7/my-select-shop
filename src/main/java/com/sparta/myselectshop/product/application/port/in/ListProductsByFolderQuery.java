package com.sparta.myselectshop.product.application.port.in;

import com.sparta.myselectshop.product.domain.Product;
import org.springframework.data.domain.Page;

public interface ListProductsByFolderQuery {
    Page<Product> listProductsByFolder(ListProductsByFolderCommand command);
}
