package com.sparta.myselectshop.user.application.port.out;

import static com.sparta.myselectshop.user.domain.User.Password;

public interface EncodePasswordPort {
    Password encodePassword(Password rawPassword);
}
