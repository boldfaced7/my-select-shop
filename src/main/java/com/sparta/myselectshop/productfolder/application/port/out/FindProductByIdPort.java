package com.sparta.myselectshop.productfolder.application.port.out;

import java.util.Optional;

public interface FindProductByIdPort {
    Optional<FindProductByIdResponse> findById(FindProductByIdRequest request);
}
