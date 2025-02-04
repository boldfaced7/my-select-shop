package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.ListAllProductsQuery;
import com.sparta.myselectshop.product.application.port.out.FindAllProductsPort;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListAllProductsService implements ListAllProductsQuery {

    private final FindAllProductsPort findAllProductsPort;

    @Override
    public List<Product> listAllProducts() {
        return findAllProductsPort.findAll();
    }
}
