package com.sparta.myselectshop.product.application.port.in;

import com.sparta.myselectshop.product.domain.Product;

public interface UpdateLowestPriceUseCase {
    Product updateLowestPrice(UpdateLowestPriceCommand command);
}
