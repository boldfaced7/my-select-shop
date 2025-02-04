package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.application.port.in.ListAllProductsQuery;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ListAllProductsController {

    private final ListAllProductsQuery listAllProductsQuery;

    @PutMapping("/products")
    public ResponseEntity<List<Response>> listAllProducts() {
        return listAllProductsQuery.listAllProducts()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        ResponseEntity::ok
                ));
    }

    public Response toResponse(Product product) {
        return new Response(
                product.getId(),
                product.getTitle(),
                product.getImage(),
                product.getLink(),
                product.getLowestPrice(),
                product.getMyPrice()
        );
    }

    public record Response(
            String id,
            String title,
            String image,
            String link,
            int lowestPrice,
            int myPrice
    ) {}
}
