package com.example.picpay_hexagonal.user.application.repository;

import com.example.picpay_hexagonal.user.domain.User;

public interface UserRepository {
     User salva(User user);
}
