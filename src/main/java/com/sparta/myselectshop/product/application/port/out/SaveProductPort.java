package com.sparta.myselectshop.product.application.port.out;

import com.sparta.myselectshop.product.domain.Product;

public interface SaveProductPort {
    Product save(Product product);
}
