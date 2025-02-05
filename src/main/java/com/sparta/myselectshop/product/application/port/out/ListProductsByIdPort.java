package com.sparta.myselectshop.product.application.port.out;

import com.sparta.myselectshop.product.domain.Product;

import java.util.List;

import static com.sparta.myselectshop.product.domain.Product.Id;

public interface ListProductsByIdPort {
    List<Product> listByIdIn(List<Id> ids);
}
