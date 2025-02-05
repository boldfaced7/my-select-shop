package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.application.port.in.RegisterProductCommand;
import com.sparta.myselectshop.product.application.port.in.RegisterProductUseCase;
import com.sparta.myselectshop.product.domain.Product;
import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sparta.myselectshop.product.domain.Product.*;

@RestController
@RequiredArgsConstructor
public class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;

    @PostMapping("/products")
    public ProductResponse  createProduct(
            @RequestBody final ProductRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        var command = toCommand(request, userDetails);
        var registered = registerProductUseCase.registerProduct(command);
        return toResponse(registered);
    }

    public RegisterProductCommand toCommand(
            ProductRequest request,
            UserDetailsImpl userDetails
    ) {
        return new RegisterProductCommand(
                new UserId(userDetails.getUserId()),
                new Title(request.title()),
                new Image(request.image()),
                new Link(request.link()),
                new LowestPrice(request.lowestPrice())
        );
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getImage(),
                product.getLink(),
                product.getLowestPrice(),
                product.getMyPrice()
        );
    }

    public record ProductRequest(
            String title,
            String image,
            String link,
            int lowestPrice
    ) {}

    public record ProductResponse(
            String id,
            String title,
            String image,
            String link,
            int lowestPrice,
            int myPrice
    ) {}
}
