package com.example.picpay_hexagonal.user.application.api;

import com.example.picpay_hexagonal.user.domain.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserResponse {
    private Long id;

    public UserResponse(User user) {
        this.id = user.getId();
    }
}
