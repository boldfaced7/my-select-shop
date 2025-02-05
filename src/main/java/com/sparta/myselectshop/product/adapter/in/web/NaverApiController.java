package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.adapter.out.external.NaverSearchApiClient;
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

    private Response toResponse(NaverSearchApiClient.Item item) {
        return new Response(
                item.title(),
                item.link(),
                item.image(),
                item.lprice()
        );
    }

    public record Response(
            String title,
            String link,
            String image,
            int lprice
    ) {}
}