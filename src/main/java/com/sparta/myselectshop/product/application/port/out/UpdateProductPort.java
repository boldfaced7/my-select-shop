package com.sparta.myselectshop.product.application.port.out;

import com.sparta.myselectshop.product.domain.Product;

public interface UpdateProductPort {
    Product update(Product product);
}
