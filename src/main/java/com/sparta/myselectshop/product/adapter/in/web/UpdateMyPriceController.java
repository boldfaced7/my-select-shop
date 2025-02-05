package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.application.port.in.UpdateMyPriceCommand;
import com.sparta.myselectshop.product.application.port.in.UpdateMyPriceUseCase;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sparta.myselectshop.product.domain.Product.Id;
import static com.sparta.myselectshop.product.domain.Product.MyPrice;

@RestController
@RequiredArgsConstructor
public class UpdateMyPriceController {

    private final UpdateMyPriceUseCase updateMyPriceUseCase;

    @PutMapping("/products/{productId}")
    public MyPriceResponse  createProduct(
            @PathVariable String productId,
            @RequestBody MyPriceRequest request
    ) {
        var command = toCommand(productId, request);
        var registered = updateMyPriceUseCase.updateMyPrice(command);
        return toResponse(registered);
    }

    public UpdateMyPriceCommand toCommand(String productId, MyPriceRequest request) {
        return new UpdateMyPriceCommand(
                new Id(productId),
                new MyPrice(request.myPrice())
        );
    }

    public MyPriceResponse toResponse(Product product) {
        return new MyPriceResponse(
                product.getId(),
                product.getTitle(),
                product.getImage(),
                product.getLink(),
                product.getLowestPrice(),
                product.getMyPrice()
        );
    }

    public record MyPriceRequest(
            int myPrice
    ) {}

    public record MyPriceResponse(
            String id,
            String title,
            String image,
            String link,
            int lowestPrice,
            int myPrice
    ) {}
}
