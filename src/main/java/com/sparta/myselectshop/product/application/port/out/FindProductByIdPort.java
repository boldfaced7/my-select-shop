package com.sparta.myselectshop.product.application.port.out;

import com.sparta.myselectshop.product.domain.Product;

import java.util.Optional;

public interface FindProductByIdPort {
    Optional<Product> findById(Product.Id id);
}
