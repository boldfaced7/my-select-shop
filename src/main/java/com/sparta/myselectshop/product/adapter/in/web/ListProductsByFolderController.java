package com.sparta.myselectshop.product.adapter.in.web;

import com.sparta.myselectshop.product.application.port.in.ListProductsByFolderCommand;
import com.sparta.myselectshop.product.application.port.in.ListProductsByFolderQuery;
import com.sparta.myselectshop.product.domain.Product;
import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sparta.myselectshop.product.domain.Product.UserId;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ListProductsByFolderController {

    private final ListProductsByFolderQuery listProductsByFolderQuery;

    @GetMapping("/folders/{folderId}/products")
    public ResponseEntity<Page<Response>> listPagedProductsByUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String folderId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        var command = toCommand(userDetails, folderId, page, size, sortBy, isAsc);
        var responses = listProductsByFolderQuery
                .listProductsByFolder(command)
                .map(this::toResponse);

        return ResponseEntity.ok(responses);
    }

    private ListProductsByFolderCommand toCommand(
            UserDetailsImpl userDetails,
            String folderId,
            int page,
            int size,
            String sortBy,
            boolean isAsc
    ) {
        return new ListProductsByFolderCommand(
                new UserId(userDetails.getUserId()),
                folderId,
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
