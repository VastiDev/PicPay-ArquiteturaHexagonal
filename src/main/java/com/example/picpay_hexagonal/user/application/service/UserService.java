package com.example.picpay_hexagonal.user.application.service;

import com.example.picpay_hexagonal.user.application.api.UserDetailedResponse;
import com.example.picpay_hexagonal.user.application.api.UserListResponse;
import com.example.picpay_hexagonal.user.application.api.UserRequest;
import com.example.picpay_hexagonal.user.application.api.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserResponse criaUser(UserRequest userRequest);

    UserDetailedResponse getUserById(Long id);

    List<UserListResponse> getAllUsers();
}
