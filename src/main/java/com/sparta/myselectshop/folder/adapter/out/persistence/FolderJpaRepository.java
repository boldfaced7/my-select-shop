package com.sparta.myselectshop.folder.adapter.out.persistence;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FolderJpaRepository extends Repository<FolderJpaEntity, Long> {
    boolean existsByNameIn(List<String> names);
    Optional<FolderJpaEntity> findById(Long id);
    List<FolderJpaEntity> findByUserId(String userId);
    List<FolderJpaEntity> saveAll(Iterable<FolderJpaEntity> entities);
}
