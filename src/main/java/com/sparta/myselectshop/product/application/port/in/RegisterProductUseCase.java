package com.sparta.myselectshop.product.application.port.in;

import com.sparta.myselectshop.product.domain.Product;

public interface RegisterProductUseCase {
    Product registerProduct(RegisterProductCommand command);
}
