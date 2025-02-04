package com.sparta.myselectshop.productfolder.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductFolder {

    private final String id;
    private final String userId;
    private final String productId;
    private final String folderId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    public static ProductFolder generate(
            UserId userId,
            ProductId productId,
            FolderId folderId
    ) {
        return new ProductFolder(
                null,
                userId.value(),
                productId.value(),
                folderId.value(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static ProductFolder generate(
            Id id,
            UserId userId,
            ProductId productId,
            FolderId folderId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new ProductFolder(
                id.value(),
                userId.value(),
                productId.value(),
                folderId.value(),
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public record Id(@NotBlank String value) {}
    public record UserId(@NotBlank String value) {}
    public record ProductId(@NotBlank String value) {}
    public record FolderId(@NotBlank String value) {}
}
