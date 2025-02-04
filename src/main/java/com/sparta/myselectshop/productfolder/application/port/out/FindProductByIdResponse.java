package com.sparta.myselectshop.productfolder.application.port.out;

public record FindProductByIdResponse(
        String productId,
        String userId,
        String title
) {
}
