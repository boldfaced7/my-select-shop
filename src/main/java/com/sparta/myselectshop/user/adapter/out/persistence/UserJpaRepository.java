package com.sparta.myselectshop.user.adapter.out.persistence;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserJpaRepository extends Repository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findById(Long id);
    Optional<UserJpaEntity> findByEmail(String email);
    Optional<UserJpaEntity> findByUsername(String username);
    UserJpaEntity save(UserJpaEntity user);
}
