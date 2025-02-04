package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.UpdateMyPriceCommand;
import com.sparta.myselectshop.product.application.port.in.UpdateMyPriceUseCase;
import com.sparta.myselectshop.product.application.port.out.FindProductByIdPort;
import com.sparta.myselectshop.product.application.port.out.UpdateProductPort;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateMyPriceService implements UpdateMyPriceUseCase {

    private final FindProductByIdPort findProductByIdPort;
    private final UpdateProductPort updateProductPort;

    @Override
    public Product updateMyPrice(UpdateMyPriceCommand command) {
        return findProductByIdPort.findById(command.id())
                .map(found -> found.updateMyPrice(command.myPrice()))
                .map(updateProductPort::update)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));
    }
}
