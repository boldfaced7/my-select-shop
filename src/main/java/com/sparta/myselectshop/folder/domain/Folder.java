package com.sparta.myselectshop.folder.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Folder {
    private final String id;
    private final String userId;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    public static Folder generate(
            UserId userId,
            Name name
    ) {
        return new Folder(
                null,
                userId.value(),
                name.value(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static Folder generate(
            Id id,
            UserId userId,
            Name name,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new Folder(
                id.value(),
                userId.value(),
                name.value(),
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public record Id(String value) {}
    public record UserId(String value) {}
    public record Name(String value) {}
}
