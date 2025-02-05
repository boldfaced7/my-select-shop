package com.sparta.myselectshop.productfolder.adaper.out.internal;

import com.sparta.myselectshop.common.IdParser;
import com.sparta.myselectshop.product.adapter.out.persistence.ProductJpaEntity;
import com.sparta.myselectshop.product.adapter.out.persistence.ProductJpaRepository;
import com.sparta.myselectshop.productfolder.application.port.out.FindProductByIdPort;
import com.sparta.myselectshop.productfolder.domain.ProductFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindProductByIdAdapter implements FindProductByIdPort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<ProductResponse> findById(ProductRequest productRequest) {
        Long productId = IdParser.parseLong(productRequest.productId().value());
        return productJpaRepository.findById(productId)
                .map(this::toResponse);
    }

    private ProductResponse toResponse(ProductJpaEntity product) {
        return new ProductResponse(
                new ProductFolder.ProductId(product.getId().toString()),
                new ProductFolder.UserId(product.getUserId()),
                product.getTitle()
        );
    }
}
