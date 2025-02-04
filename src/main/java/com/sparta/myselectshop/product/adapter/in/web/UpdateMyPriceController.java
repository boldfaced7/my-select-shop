package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.application.port.in.UpdateMyPriceCommand;
import com.sparta.myselectshop.product.application.port.in.UpdateMyPriceUseCase;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.sparta.myselectshop.product.domain.Product.Id;
import static com.sparta.myselectshop.product.domain.Product.MyPrice;

@Controller
@RequiredArgsConstructor
public class UpdateMyPriceController {

    private final UpdateMyPriceUseCase updateMyPriceUseCase;

    @PutMapping("/products/{productId}")
    public ResponseEntity<Response>  createProduct(
            @PathVariable String productId,
            @RequestBody Request request
    ) {
        var command = toCommand(productId, request);
        var registered = updateMyPriceUseCase.updateMyPrice(command);
        var response = toResponse(registered);
        return ResponseEntity.ok(response);
    }

    public UpdateMyPriceCommand toCommand(String productId, Request request) {
        return new UpdateMyPriceCommand(
                new Id(productId),
                new MyPrice(request.myPrice())
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
            int myPrice
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
