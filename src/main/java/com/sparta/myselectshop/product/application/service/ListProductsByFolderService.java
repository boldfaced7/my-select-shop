package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.ListProductsByFolderCommand;
import com.sparta.myselectshop.product.application.port.in.ListProductsByFolderQuery;
import com.sparta.myselectshop.product.application.port.out.ListProductFoldersByFolderPort;
import com.sparta.myselectshop.product.application.port.out.ListProductsByIdPort;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sparta.myselectshop.product.application.port.out.ListProductFoldersByFolderPort.ProductFolderRequest;
import static com.sparta.myselectshop.product.application.port.out.ListProductFoldersByFolderPort.ProductFolderResponse;
import static com.sparta.myselectshop.product.domain.Product.Id;

@Service
@RequiredArgsConstructor
public class ListProductsByFolderService implements ListProductsByFolderQuery {

    private final ListProductFoldersByFolderPort listProductFoldersByFolderPort;
    private final ListProductsByIdPort listProductsByIdPort;

    @Override
    public Page<Product> listProductsByFolder(ListProductsByFolderCommand command) {
        List<Id> ids = listIdsByFolder(command.folderId(), command.pageable());
        List<Product> products = listProductsByIdPort.listByIdIn(ids);
        return new PageImpl<>(products, command.pageable(), products.size());
    }

    private List<Id> listIdsByFolder(String folderId, Pageable pageable) {
        var request = new ProductFolderRequest(folderId, pageable);

        return listProductFoldersByFolderPort.listByFolderId(request)
                .stream()
                .map(ProductFolderResponse::productId)
                .map(Id::new)
                .toList();
    }
}
