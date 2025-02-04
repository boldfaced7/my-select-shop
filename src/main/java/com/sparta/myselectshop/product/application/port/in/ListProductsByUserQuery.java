package com.sparta.myselectshop.product.application.port.in;

import com.sparta.myselectshop.product.domain.Product;
import org.springframework.data.domain.Page;

public interface ListProductsByUserQuery {
    Page<Product> listProductsByUser(ListProductsByUserCommand command);
}
