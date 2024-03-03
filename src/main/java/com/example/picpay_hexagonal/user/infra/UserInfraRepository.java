package com.example.picpay_hexagonal.user.infra;

import com.example.picpay_hexagonal.user.application.repository.UserRepository;
import com.example.picpay_hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInfraRepository implements UserRepository {
    private final UserSpringSataJpaRepository userSpringSataJpaRepository;

    @Override
    public User salva(User user) {
        userSpringSataJpaRepository.save(user);
        return user;
    }
}
