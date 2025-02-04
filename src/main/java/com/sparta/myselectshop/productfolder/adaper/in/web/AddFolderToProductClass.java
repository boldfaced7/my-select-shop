package com.sparta.myselectshop.productfolder.adaper.in.web;

import com.sparta.myselectshop.productfolder.application.port.in.AddFolderToProductCommand;
import com.sparta.myselectshop.productfolder.application.port.in.AddFolderToProductUseCase;
import com.sparta.myselectshop.user.adapter.in.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.sparta.myselectshop.productfolder.domain.ProductFolder.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddFolderToProductClass {

    private final AddFolderToProductUseCase addFolderToProductUseCase;

    @PostMapping("/products/{productId}/folder")
    public ResponseEntity<Void> addFolder(
            @PathVariable String productId,
            @RequestParam String folderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        var command = toCommand(userDetails.getUserId(), productId, folderId);
        addFolderToProductUseCase.addFolderToProduct(command);
        return ResponseEntity.ok().build();
    }

    private AddFolderToProductCommand toCommand(
            String userId,
            String productId,
            String folderId
    ) {
        return new AddFolderToProductCommand(
                new UserId(userId),
                new ProductId(productId),
                new FolderId(folderId)
        );
    }
}
