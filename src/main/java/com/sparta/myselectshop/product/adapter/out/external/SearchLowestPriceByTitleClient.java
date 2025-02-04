package com.sparta.myselectshop.product.adapter.out.external;

import java.util.Optional;

import static com.sparta.myselectshop.product.domain.Product.*;

public interface SearchLowestPriceByTitleClient {
    Optional<LowestPrice> searchLowestPriceByTitle(String title);
}
