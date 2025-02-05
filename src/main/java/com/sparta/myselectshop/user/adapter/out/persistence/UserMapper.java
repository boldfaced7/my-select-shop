package com.sparta.myselectshop.user.adapter.out.persistence;

import com.sparta.myselectshop.common.IdParser;
import com.sparta.myselectshop.user.domain.User;

import static com.sparta.myselectshop.user.domain.User.*;

public class UserMapper {

    public static User toDomain(UserJpaEntity jpaEntity) {
        return generate(
                new Id(jpaEntity.getId().toString()),
                new Username(jpaEntity.getUsername()),
                new Password(jpaEntity.getPassword()),
                new Email(jpaEntity.getEmail()),
                jpaEntity.getRole(),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt(),
                jpaEntity.getDeletedAt()
        );
    }

    public static UserJpaEntity toJpa(User domain) {
        return new UserJpaEntity(
                IdParser.parseLong(domain.getId()),
                domain.getUsername(),
                domain.getPassword(),
                domain.getEmail(),
                domain.getRole(),
                domain.getCreatedAt(),
                domain.getUpdatedAt(),
                domain.getDeletedAt()
        );
    }
}
