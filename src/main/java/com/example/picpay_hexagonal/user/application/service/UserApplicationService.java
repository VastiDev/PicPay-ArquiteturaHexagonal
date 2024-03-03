package com.example.picpay_hexagonal.user.application.service;

import com.example.picpay_hexagonal.user.application.api.UserDetailedResponse;
import com.example.picpay_hexagonal.user.application.api.UserRequest;
import com.example.picpay_hexagonal.user.application.api.UserResponse;
import com.example.picpay_hexagonal.user.application.repository.UserRepository;
import com.example.picpay_hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse criaUser(UserRequest userRequest) {
        User user = userRepository.salva(new User(userRequest));
        return new UserResponse(user);
    }

    @Override
    public UserDetailedResponse getUserById(Long id)  {
        User user = userRepository.getUserById(id);
        return new UserDetailedResponse(user);
    }
}
