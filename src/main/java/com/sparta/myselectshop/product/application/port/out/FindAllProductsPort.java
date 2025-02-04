package com.sparta.myselectshop.product.application.port.out;

import com.sparta.myselectshop.product.domain.Product;

import java.util.List;

public interface FindAllProductsPort {
    List<Product> findAll();
}
