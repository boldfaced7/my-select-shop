package com.sparta.myselectshop.product.application.port.in;


import static com.sparta.myselectshop.product.domain.Product.*;

public record UpdateMyPriceCommand(
        Id id,
        MyPrice myPrice
) {
}
