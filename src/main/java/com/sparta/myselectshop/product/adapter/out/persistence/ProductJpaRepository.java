package com.sparta.myselectshop.product.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends Repository<ProductJpaEntity, Long> {
    Optional<ProductJpaEntity> findById(Long id);
    List<ProductJpaEntity> findAll();
    List<ProductJpaEntity> findByIdIn(List<Long> ids);
    Page<ProductJpaEntity> findByUserId(String userId, Pageable pageable);
    ProductJpaEntity save(ProductJpaEntity product);
}
