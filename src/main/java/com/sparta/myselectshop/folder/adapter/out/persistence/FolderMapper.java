package com.sparta.myselectshop.folder.adapter.out.persistence;

import com.sparta.myselectshop.common.IdParser;
import com.sparta.myselectshop.folder.domain.Folder;

import static com.sparta.myselectshop.folder.domain.Folder.*;

public class FolderMapper {

    public static Folder toDomain(FolderJpaEntity jpaEntity) {
        return generate(
                new Id(jpaEntity.getId().toString()),
                new UserId(jpaEntity.getUserId()),
                new Name(jpaEntity.getName()),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt(),
                jpaEntity.getDeletedAt()
        );
    }

    public static FolderJpaEntity toJpa(Folder domain) {
        return new FolderJpaEntity(
                IdParser.parseLong(domain.getId()),
                domain.getUserId(),
                domain.getName(),
                domain.getCreatedAt(),
                domain.getUpdatedAt(),
                domain.getDeletedAt()
        );
    }
}
