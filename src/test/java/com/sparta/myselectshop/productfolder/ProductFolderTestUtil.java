package com.sparta.myselectshop.productfolder;

import com.sparta.myselectshop.productfolder.domain.ProductFolder;
import org.assertj.core.api.Assertions;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static com.sparta.myselectshop.productfolder.domain.ProductFolder.*;

public class ProductFolderTestUtil {
    public final static Id ID = new Id("1");
    public final static UserId USER_ID = new UserId("1");
    public final static UserId INVALID_USER_ID = new UserId("2");
    public final static ProductId PRODUCT_ID = new ProductId("1");
    public final static FolderId FOLDER_ID = new FolderId("1");


    public static ProductFolder productFolder(
            Id id,
            UserId userId,
            ProductId productId,
            FolderId folderId
    ) {
        return generate(
                id,
                userId,
                productId,
                folderId,
                LocalDateTime.MIN,
                LocalDateTime.MIN,
                null
        );
    }

    public static ProductFolder setId(ProductFolder target, Id id) {
        ReflectionTestUtils.setField(target, "id", id.value());
        return target;
    }

    public static void assertAll(
            ProductFolder productFolder,
            Id id,
            UserId userId,
            ProductId productId,
            FolderId folderId
    ) {
        Assertions.assertThat(productFolder).isNotNull();
        Assertions.assertThat(productFolder.getId()).isEqualTo(id.value());
        Assertions.assertThat(productFolder.getUserId()).isEqualTo(userId.value());
        Assertions.assertThat(productFolder.getProductId()).isEqualTo(productId.value());
        Assertions.assertThat(productFolder.getFolderId()).isEqualTo(folderId.value());
    }
}
