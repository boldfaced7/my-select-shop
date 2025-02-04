package com.sparta.myselectshop.product.application.port.in;

import static com.sparta.myselectshop.product.domain.Product.*;

public record RegisterProductCommand(
        Title title,
        Image image,
        Link link,
        LowestPrice lowestPrice
) {}
