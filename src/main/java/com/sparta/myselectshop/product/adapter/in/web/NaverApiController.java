package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.adapter.out.external.NaverSearchApiClient;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NaverApiController {

    private final NaverSearchApiClient naverSearchApiClient;

    @GetMapping("/search")
    public List<Response> searchItems(@RequestParam String query)  {
        var searched = naverSearchApiClient.searchProductsByTitle(query);

        return searched.stream()
                .map(this::toResponse)
                .toList();
    }

    private Response toResponse(Product product) {
        return new Response(
                product.getTitle(),
                product.getLink(),
                product.getImage(),
                product.getLowestPrice()
        );
    }

    public record Response(
            String title,
            String link,
            String image,
            int lprice
    ) {}
}