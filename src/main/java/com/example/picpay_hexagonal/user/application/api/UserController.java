package com.example.picpay_hexagonal.user.application.api;

import com.example.picpay_hexagonal.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;

    @Override
    public UserResponse postUser(UserRequest userRequest) {
        UserResponse userCriado = userService.criaUser(userRequest);
        return userCriado;
    }
}
