package com.sparta.myselectshop.user.adapter.out.persistence;

import com.sparta.myselectshop.user.application.port.out.FindByEmailPort;
import com.sparta.myselectshop.user.application.port.out.FindByUsernamePort;
import com.sparta.myselectshop.user.application.port.out.SaveUserPort;
import com.sparta.myselectshop.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.sparta.myselectshop.user.domain.User.Email;
import static com.sparta.myselectshop.user.domain.User.Username;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements
        FindByEmailPort,
        FindByUsernamePort,
        SaveUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByEmail(Email email) {
        return userJpaRepository.findByEmail(email.value())
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(Username username) {
        return userJpaRepository.findByUsername(username.value())
                .map(UserMapper::toDomain);
    }

    @Override
    public User save(User user) {
        var source = UserMapper.toJpa(user);
        var saved = userJpaRepository.save(source);
        return UserMapper.toDomain(saved);
    }
}
