package com.sparta.myselectshop.productfolder.application.service;

import com.sparta.myselectshop.productfolder.application.port.in.AddFolderToProductCommand;
import com.sparta.myselectshop.productfolder.application.port.in.AddFolderToProductUseCase;
import com.sparta.myselectshop.productfolder.application.port.out.FindFolderByIdPort;
import com.sparta.myselectshop.productfolder.application.port.out.FindProductByIdPort;
import com.sparta.myselectshop.productfolder.application.port.out.FindProductFolderByIdsPort;
import com.sparta.myselectshop.productfolder.application.port.out.SaveProductFolderPort;
import com.sparta.myselectshop.productfolder.domain.ProductFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sparta.myselectshop.productfolder.application.port.out.FindFolderByIdPort.FolderRequest;
import static com.sparta.myselectshop.productfolder.application.port.out.FindFolderByIdPort.FolderResponse;
import static com.sparta.myselectshop.productfolder.application.port.out.FindProductByIdPort.ProductRequest;
import static com.sparta.myselectshop.productfolder.application.port.out.FindProductByIdPort.ProductResponse;
import static com.sparta.myselectshop.productfolder.domain.ProductFolder.*;

@Service
@RequiredArgsConstructor
public class AddFolderToProductService implements AddFolderToProductUseCase {

    private final FindProductByIdPort findProductByIdPort;
    private final FindFolderByIdPort findFolderByIdPort;
    private final FindProductFolderByIdsPort findProductFolderByIdsPort;
    private final SaveProductFolderPort saveProductFolderPort;

    @Override
    public ProductFolder addFolderToProduct(AddFolderToProductCommand command) {
        var productInfo = getProductInfo(command.productId());
        var folderInfo = getFolderInfo(command.folderId());

        verifyOwnership(productInfo.userId(), folderInfo.userId(), command.userId());
        verifyUniqueness(productInfo.productId(), folderInfo.folderId());

        var toBeSaved = toEntity(command);
        return saveProductFolderPort.save(toBeSaved);
    }

    private ProductResponse getProductInfo(ProductId productId) {
        var request = new ProductRequest(productId);
        return findProductByIdPort.findById(request)
                .orElseThrow(() -> new NullPointerException("해당 상품이 존재하지 않습니다."));
    }

    private FolderResponse getFolderInfo(FolderId folderId) {
        var request = new FolderRequest(folderId);
        return findFolderByIdPort.findById(request)
                .orElseThrow(() -> new NullPointerException("해당 폴더가 존재하지 않습니다."));
    }

    private void verifyOwnership(
            UserId productUserId,
            UserId folderUserId,
            UserId requestingUserId
    ) {
        if (!(productUserId.equals(requestingUserId)
                && folderUserId.equals(requestingUserId))) {
            throw new IllegalArgumentException(
                    "회원님의 관심상품이 아니거나, 회원님의 폴더가 아닙니다.");
        }
    }

    private void verifyUniqueness(
            ProductId productId,
            FolderId folderId
    ) {
        findProductFolderByIdsPort.findByIds(productId, folderId)
                .ifPresent(productFolder ->
                        {throw new IllegalArgumentException("중복된 폴더입니다.");});

    }

    private ProductFolder toEntity(AddFolderToProductCommand command) {
        return generate(
                command.userId(),
                command.productId(),
                command.folderId()
        );
    }
}
