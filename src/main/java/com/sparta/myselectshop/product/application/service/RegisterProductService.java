package com.sparta.myselectshop.product.application.service;

import com.sparta.myselectshop.product.application.port.in.RegisterProductCommand;
import com.sparta.myselectshop.product.application.port.in.RegisterProductUseCase;
import com.sparta.myselectshop.product.application.port.out.SaveProductPort;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sparta.myselectshop.product.domain.Product.generate;

@Service
@RequiredArgsConstructor
public class RegisterProductService implements RegisterProductUseCase {

    private final SaveProductPort saveProductPort;

    @Override
    public Product registerProduct(RegisterProductCommand command) {
        var toBeSaved = generate(
                command.userId(),
                command.title(),
                command.image(),
                command.link(),
                command.lowestPrice()
        );
        return saveProductPort.save(toBeSaved);
    }
}
