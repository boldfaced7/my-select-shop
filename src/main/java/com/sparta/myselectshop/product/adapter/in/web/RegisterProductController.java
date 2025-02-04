package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.application.port.in.RegisterProductCommand;
import com.sparta.myselectshop.product.application.port.in.RegisterProductUseCase;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;

    @PostMapping("/products")
    public ResponseEntity<Response>  createProduct(
            @RequestBody final Request request
    ) {
        var command = toCommand(request);
        var registered = registerProductUseCase.registerProduct(command);
        var response = toResponse(registered);
        return ResponseEntity.ok(response);
    }

    public RegisterProductCommand toCommand(final Request request) {
        return new RegisterProductCommand(
                new Product.Title(request.title()),
                new Product.Image(request.image()),
                new Product.Link(request.link()),
                new Product.LowestPrice(request.lowestPrice())
        );
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

    public record Request(
            String title,
            String image,
            String link,
            int lowestPrice
    ) {}

    public record Response(
            String id,
            String title,
            String image,
            String link,
            int lowestPrice,
            int myPrice
    ) {}
}
