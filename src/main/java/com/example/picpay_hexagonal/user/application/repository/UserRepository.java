package com.example.picpay_hexagonal.user.application.repository;

import com.example.picpay_hexagonal.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
     User salva(User user);

     User getUserById(Long id);

     List<User> getAllUsers();
}
