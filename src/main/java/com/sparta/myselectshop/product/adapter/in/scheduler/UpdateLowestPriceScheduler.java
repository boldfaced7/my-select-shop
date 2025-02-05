package com.sparta.myselectshop.product.adapter.in.scheduler;

import com.sparta.myselectshop.product.adapter.out.external.NaverSearchApiClient;
import com.sparta.myselectshop.product.application.port.in.ListAllProductsQuery;
import com.sparta.myselectshop.product.application.port.in.UpdateLowestPriceCommand;
import com.sparta.myselectshop.product.application.port.in.UpdateLowestPriceUseCase;
import com.sparta.myselectshop.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "scheduler")
@Component
@RequiredArgsConstructor
public class UpdateLowestPriceScheduler {

    private static final int SLEEP_DURATION_SECONDS = 1;

    private final ListAllProductsQuery listAllProductsQuery;
    private final NaverSearchApiClient naverSearchApiClient;
    private final UpdateLowestPriceUseCase updateLowestPriceUseCase;

    @Scheduled(cron = "0 0 1 * * * ")
    public void updateLowestPrice() {
        log.info("Updating lowest price from Naver");

        listAllProductsQuery.listAllProducts()
                .stream()
                .map(this::sleep)
                .map(this::searchLowestPrice)
                .forEach(updateLowestPriceUseCase::updateLowestPrice);
    }

    private Product sleep(Product product) {
        try {
            TimeUnit.SECONDS.sleep(SLEEP_DURATION_SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 현재 스레드 인터럽트 상태 복원
            log.error("Interrupted while updating lowest price: {}", product.getTitle(), e);
        }
        return product;
    }

    private UpdateLowestPriceCommand searchLowestPrice(Product product) {
        var found = naverSearchApiClient
                .searchProductsByTitle(product.getTitle())
                .get(0)
                .lprice();

        return new UpdateLowestPriceCommand(
                new Product.Id(product.getId()),
                new Product.LowestPrice(found)
        );
    }
}
