package com.example.picpay_hexagonal.user.application.service;

import com.example.picpay_hexagonal.user.application.api.UserDetailedResponse;
import com.example.picpay_hexagonal.user.application.api.UserRequest;
import com.example.picpay_hexagonal.user.application.api.UserResponse;
import org.springframework.stereotype.Service;


public interface UserService {
    UserResponse criaUser(UserRequest userRequest);

    UserDetailedResponse getUserById(Long id);
}
