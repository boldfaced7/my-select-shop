package com.sparta.myselectshop.product;

import com.sparta.myselectshop.product.domain.Product;
import org.assertj.core.api.Assertions;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static com.sparta.myselectshop.product.domain.Product.*;

public class ProductTestUtil {
    public final static Id ID = new Id("1");
    public final static UserId USER_ID = new UserId("1");
    public final static Title TITLE = new Title("Title");
    public final static Image IMAGE = new Image("Image");
    public final static Link LINK = new Link("Link");
    public final static LowestPrice LOWEST_PRICE = new LowestPrice(10000);
    public final static LowestPrice NEW_LOWEST_PRICE = new LowestPrice(10001);
    public final static MyPrice MY_PRICE = new MyPrice(10000);
    public final static MyPrice NEW_MY_PRICE = new MyPrice(10001);
    public final static String PRODUCT_FOLDER_ID = "1";
    public final static String FOLDER_ID = "1";


    public static Product product(
            Id id,
            UserId userId,
            Title title,
            Image image,
            Link link,
            LowestPrice lowestPrice,
            MyPrice myPrice
    ) {
        return generate(
                id,
                userId,
                title,
                image,
                link,
                lowestPrice,
                myPrice,
                LocalDateTime.MIN,
                LocalDateTime.MIN,
                null
        );
    }

    public static Product setId(Product target, Id id) {
        ReflectionTestUtils.setField(target, "id", id.value());
        return target;
    }

    public static void assertAll(
            Product product,
            Id id,
            UserId userId,
            Title title,
            Image image,
            Link link,
            LowestPrice lowestPrice,
            MyPrice myPrice
    ) {
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getId()).isEqualTo(id.value());
        Assertions.assertThat(product.getUserId()).isEqualTo(userId.value());
        Assertions.assertThat(product.getTitle()).isEqualTo(title.value());
        Assertions.assertThat(product.getImage()).isEqualTo(image.value());
        Assertions.assertThat(product.getLink()).isEqualTo(link.value());
        Assertions.assertThat(product.getLowestPrice()).isEqualTo(lowestPrice.value());
        Assertions.assertThat(product.getMyPrice()).isEqualTo(myPrice.value());
    }

    public static void assertAll(
            Product product,
            Id id,
            UserId userId,
            Title title,
            Image image,
            Link link,
            LowestPrice lowestPrice
    ) {
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getId()).isEqualTo(id.value());
        Assertions.assertThat(product.getUserId()).isEqualTo(userId.value());
        Assertions.assertThat(product.getTitle()).isEqualTo(title.value());
        Assertions.assertThat(product.getImage()).isEqualTo(image.value());
        Assertions.assertThat(product.getLink()).isEqualTo(link.value());
        Assertions.assertThat(product.getLowestPrice()).isEqualTo(lowestPrice.value());
    }
}
