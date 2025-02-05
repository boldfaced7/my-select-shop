package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.application.port.in.ListProductsByUserCommand;
import com.sparta.myselectshop.product.application.port.in.ListProductsByUserQuery;
import com.sparta.myselectshop.product.domain.Product;
import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sparta.myselectshop.product.domain.Product.UserId;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ListProductsByUserController {

    private final ListProductsByUserQuery listProductsByUserQuery;

    @GetMapping("/products")
    public Page<Response> listPagedProductsByUser(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        var command = toCommand(userDetails, page, size, sortBy, isAsc);
        return listProductsByUserQuery
                .listProductsByUser(command)
                .map(this::toResponse);

    }

    private ListProductsByUserCommand toCommand(
            UserDetailsImpl userDetails,
            int page,
            int size,
            String sortBy,
            boolean isAsc
    ) {
        return new ListProductsByUserCommand(
                new UserId(userDetails.getUserId()),
                page,
                size,
                sortBy,
                isAsc
        );
    }

    private Response toResponse(Product product) {
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
