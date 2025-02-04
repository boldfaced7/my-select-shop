package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.ListProductsByUserCommand;
import com.sparta.myselectshop.product.application.port.in.ListProductsByUserQuery;
import com.sparta.myselectshop.product.application.port.out.ListProductsByUserPort;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListProductsByUserService implements ListProductsByUserQuery {

    private final ListProductsByUserPort listProductsByUserPort;

    @Override
    public Page<Product> listProductsByUser(ListProductsByUserCommand command) {
        return listProductsByUserPort.findByUserId(command.userId(), command.pageable());
    }
}
