package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.application.port.in.RegisterProductCommand;
import com.sparta.myselectshop.product.application.port.in.RegisterProductUseCase;
import com.sparta.myselectshop.product.domain.Product;
import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.sparta.myselectshop.product.domain.Product.*;

@Controller
@RequiredArgsConstructor
public class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;

    @PostMapping("/products")
    public ResponseEntity<Response>  createProduct(
            @RequestBody final Request request,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        var command = toCommand(request, userDetails);
        var registered = registerProductUseCase.registerProduct(command);
        var response = toResponse(registered);
        return ResponseEntity.ok(response);
    }

    public RegisterProductCommand toCommand(
            Request request,
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
