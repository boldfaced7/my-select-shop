package com.sparta.myselectshop.product.application.port.in;

import com.sparta.myselectshop.product.domain.Product;

import java.util.List;

public interface ListAllProductsQuery {
    List<Product> listAllProducts();
}
