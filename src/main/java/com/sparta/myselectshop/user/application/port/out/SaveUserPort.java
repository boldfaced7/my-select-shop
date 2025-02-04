package com.sparta.myselectshop.user.application.port.out;

import com.sparta.myselectshop.user.domain.User;

public interface SaveUserPort {
    User save(User user);
}
