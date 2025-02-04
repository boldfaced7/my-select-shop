package com.sparta.myselectshop.product.application.port.in;

import com.sparta.myselectshop.product.domain.Product;

import static com.sparta.myselectshop.product.domain.Product.Id;

public record UpdateLowestPriceCommand(
        Id id,
        Product.LowestPrice lowestPrice
) {
}
