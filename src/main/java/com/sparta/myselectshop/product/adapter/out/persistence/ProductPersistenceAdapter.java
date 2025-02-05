package com.sparta.myselectshop.product.adapter.out.persistence;

import com.sparta.myselectshop.common.IdParser;
import com.sparta.myselectshop.product.application.port.out.*;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.sparta.myselectshop.product.domain.Product.Id;
import static com.sparta.myselectshop.product.domain.Product.UserId;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements
        FindProductByIdPort,
        ListAllProductsPort,
        ListProductsByIdPort,
        ListProductsByUserPort,
        SaveProductPort,
        UpdateProductPort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<Product> findById(Id id) {
        var parsedId = IdParser.parseLong(id.value());
        return productJpaRepository.findById(parsedId)
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> listByIdIn(List<Id> ids) {
        var parsedIds = ids.stream()
                .map(Id::value)
                .map(IdParser::parseLong)
                .toList();

        return productJpaRepository.findByIdIn(parsedIds)
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public Page<Product> findByUserId(UserId userId, Pageable pageable) {
        return productJpaRepository.findByUserId(userId.value(), pageable)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Product save(Product product) {
        return persist(product);
    }

    @Override
    public Product update(Product product) {
        return persist(product);
    }

    private Product persist(Product product) {
        var source = ProductMapper.toJpa(product);
        ProductJpaEntity persisted = productJpaRepository.save(source);
        return ProductMapper.toDomain(persisted);
    }
}
