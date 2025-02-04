package com.sparta.myselectshop.product.application.port.out;

import com.sparta.myselectshop.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.sparta.myselectshop.product.domain.Product.UserId;

public interface ListProductsByUserPort {
    Page<Product> findByUserId(UserId userId, Pageable pageable);
}
