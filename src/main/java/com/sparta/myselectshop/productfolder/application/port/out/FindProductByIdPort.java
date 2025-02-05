package com.sparta.myselectshop.productfolder.application.port.out;

import java.util.Optional;

import static com.sparta.myselectshop.productfolder.domain.ProductFolder.ProductId;
import static com.sparta.myselectshop.productfolder.domain.ProductFolder.UserId;

public interface FindProductByIdPort {
    Optional<ProductResponse> findById(ProductRequest productRequest);

    record ProductRequest(
            ProductId productId
    ) {}

    record ProductResponse(
            ProductId productId,
            UserId userId,
            String title
    ) {}

}
