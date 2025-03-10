package com.sparta.myselectshop.product.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    public static final int MIN_MY_PRICE = 100;

    private final String id;
    private final String userId;
    private final String title;
    private final String image;
    private final String link;
    private final Integer lowestPrice;
    private final Integer myPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    public static Product generate(
            UserId userId,
            Title title,
            Image image,
            Link link,
            LowestPrice lowestPrice
    ) {
        return new Product(
                null,
                userId.value(),
                title.value(),
                image.value(),
                link.value(),
                lowestPrice.value(),
                0,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static Product generate(
            Id id,
            UserId userId,
            Title title,
            Image image,
            Link link,
            LowestPrice lowestPrice,
            MyPrice myPrice,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new Product(
                id.value(),
                userId.value(),
                title.value(),
                image.value(),
                link.value(),
                lowestPrice.value(),
                myPrice.value(),
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public Product updateMyPrice(MyPrice toBeUpdated) {
        return new Product(
                this.id,
                this.userId,
                this.title,
                this.image,
                this.link,
                this.lowestPrice,
                toBeUpdated.value(),
                this.createdAt,
                LocalDateTime.now(),
                this.deletedAt
        );
    }

    public Product updateLowestPrice(LowestPrice toBeUpdated) {
        return new Product(
                this.id,
                this.userId,
                this.title,
                this.image,
                this.link,
                toBeUpdated.value(),
                this.myPrice,
                this.createdAt,
                LocalDateTime.now(),
                this.deletedAt
        );
    }



    public record Id(@NotBlank String value) {}
    public record UserId(@NotBlank String value) {}
    public record Title(@NotBlank String value) {}
    public record Image(String value) {}
    public record Link(String value) {}
    public record LowestPrice(int value) {}
    public record MyPrice(@Min(value = MIN_MY_PRICE) int value) {}
}
