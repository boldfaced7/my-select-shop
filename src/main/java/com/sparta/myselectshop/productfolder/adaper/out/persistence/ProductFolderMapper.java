package com.sparta.myselectshop.productfolder.adaper.out.persistence;

import com.sparta.myselectshop.common.IdParser;
import com.sparta.myselectshop.productfolder.domain.ProductFolder;

import static com.sparta.myselectshop.productfolder.domain.ProductFolder.*;

public class ProductFolderMapper {

    public static ProductFolder toDomain(ProductFolderJpaEntity jpaEntity) {
        return generate(
                new Id(jpaEntity.getId().toString()),
                new UserId(jpaEntity.getUserId()),
                new ProductId(jpaEntity.getProductId()),
                new FolderId(jpaEntity.getFolderId()),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt(),
                jpaEntity.getDeletedAt()
        );
    }

    public static ProductFolderJpaEntity toJpa(ProductFolder domain) {
        return new ProductFolderJpaEntity(
                IdParser.parseLong(domain.getId()),
                domain.getUserId(),
                domain.getProductId(),
                domain.getFolderId(),
                domain.getCreatedAt(),
                domain.getUpdatedAt(),
                domain.getDeletedAt()
        );
    }
}
