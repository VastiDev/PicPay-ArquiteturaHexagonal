package com.example.picpay_hexagonal.user.infra;

import com.example.picpay_hexagonal.handler.APIException;
import com.example.picpay_hexagonal.user.application.repository.UserRepository;
import com.example.picpay_hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserInfraRepository implements UserRepository {
    private final UserSpringSataJpaRepository userSpringSataJpaRepository;

    @Override
    public User salva(User user) {
        userSpringSataJpaRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Long id)  {
        User user = userSpringSataJpaRepository.findById(id)
                .orElseThrow(()-> APIException.build(HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"));
        return user;
    }
}
