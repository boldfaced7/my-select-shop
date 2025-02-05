package com.sparta.myselectshop.product.adapter.out.persistence;

import com.sparta.myselectshop.common.IdParser;
import com.sparta.myselectshop.product.domain.Product;

import static com.sparta.myselectshop.product.domain.Product.*;

public class ProductMapper {

    public static Product toDomain(ProductJpaEntity jpaEntity) {
        return generate(
                new Id(jpaEntity.getId().toString()),
                new UserId(jpaEntity.getUserId()),
                new Title(jpaEntity.getTitle()),
                new Image(jpaEntity.getImage()),
                new Link(jpaEntity.getLink()),
                new LowestPrice(jpaEntity.getLowestPrice()),
                new MyPrice(jpaEntity.getMyPrice()),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt(),
                jpaEntity.getDeletedAt()
        );
    }

    public static ProductJpaEntity toJpa(Product domain) {
        return new ProductJpaEntity(
                IdParser.parseLong(domain.getId()),
                domain.getUserId(),
                domain.getTitle(),
                domain.getImage(),
                domain.getLink(),
                domain.getLowestPrice(),
                domain.getMyPrice(),
                domain.getCreatedAt(),
                domain.getUpdatedAt(),
                domain.getDeletedAt()
        );
    }
}
